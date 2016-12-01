/*! WebUtils v1.1 | (c) sponsorschoose.org | sponsorschoose.org/license.html */
(function () {

    /*************************************************************************************
     *
     * Support for link fields, use link
     */
    var JsLinkField = function (config) {
        var url = config.url, beg = url.indexOf('{'), end = url.indexOf('}', beg + 1);
        if (end >= 0 && beg >= 0) {
            this.urlStart = url.substr(0, beg);
            this.urlEnd = url.substr(end + 1);
            this.urlField = url.substr(beg + 1, end - beg - 1);
        } else {
            this.urlStart = url;
            this.urlEnd = '';
            this.urlField = false;
        }
        jsGrid.Field.call(this, config);
    };

    JsLinkField.prototype = new jsGrid.Field({

        itemTemplate: function (value) {
            var urlVal = this.urlField ? (typeof value === 'object' && value ? value[this.urlField] : value) : '',
                url = this.urlStart + urlVal + this.urlEnd;

            return '<a href="' + url + '"><div class="js-link-field js-link-field-' + this.urlField + '"></div></a>';
        }

    });

    jsGrid.fields.link = JsLinkField;
    //End of support of date fields

    /*************************************************************************************
     *
     * Support for DATE fields, use jsDate
     */
    var JsDateField = function (config) {
        jsGrid.Field.call(this, config);
    };

    JsDateField.prototype = new jsGrid.Field({
        autosearch: true,
        readOnly: false,

        sorter: function (date1, date2) {
            return new Date(date1) - new Date(date2);
        },

        itemTemplate: function (value) {
            return new Date(value).toDateString();
        },

        filterTemplate: function () {
            if (!this.filtering)
                return "";

            var grid = this._grid,
                $result = this.filterControl = $('<div>').addClass('jsgrid-date-filters'),
                from = this.filterFrom = $("<input>").addClass('jsgrid-date-filter-from').datepicker({defaultDate: new Date()}).appendTo($result),
                to = this.filterTo = $("<input>").addClass('jsgrid-date-filter-to').datepicker({defaultDate: new Date()}).appendTo($result);

            if (this.autosearch) {
                from.on("change", function (e) {
                    grid.search();
                });
                to.on("change", function (e) {
                    grid.search();
                });
            }

            return $result;
        },

        insertTemplate: function (value) {
            return this._insertPicker = $("<input>").datepicker({defaultDate: new Date()});
        },

        editTemplate: function (value) {
            var res = this._editPicker = $("<input>").datepicker();
            if ((typeof value == 'string' && value != '')||
            		(typeof value == 'number' && value != 0)) res.datepicker("setDate", new Date(value));
            return res;
        },

        insertValue: function () {
            var val = this._insertPicker.datepicker("getDate");
            return val ? val.toISOString() : null;
        },

        filterValue: function () {
            var valFrom = this.filterFrom.datepicker("getDate"),
                valTo = this.filterTo.datepicker("getDate");
            return (valFrom ? valFrom.toISOString() : '') + '/' + (valTo ? valTo.toISOString() : '');
        },

        editValue: function () {
            var val = this._editPicker.datepicker("getDate");
            return val ? val.toISOString() : null;
        }
    });

    jsGrid.fields.jsDate = JsDateField;
    //End of support of date fields

    /*****************************************************************************
     * Functions to support additional dictionaries, for example,
     *  when the Project has University object, it requires this support
     *
     */

    var dictionaryCount = 0,
        JsDictionaryField = function (config) {
            this.source = '';
            if (!config.valueField) config.valueField = 'id';
            if (!config.textField) config.textField = 'title';
            if (typeof config.source == 'string') {
                config.items = WebUtils.readFromSelect(config.source, config.isStringValue, config.textField, config.valueField);
            } else if (Array.isArray(config.source)) {
                config.items = config.source;
                config.source = 'dictionary' + (dictionaryCount++);
                WebUtils.saveListByDomain(config.source, config.valueField, config.items);
            } else {
                console.error('Source in dictionary must be either id to select element or javascript list');
            }
            this.storedMap = WebUtils.getMapByDomainId(config.source);
            this.convertStoredValue = function (val) {
                if (this.valueType === 'number') val = parseInt(val || 0);
                return this.storedMap[val];
            };
            jsGrid.fields.select.call(this, config);
        };

    JsDictionaryField.prototype = new jsGrid.fields.select({

        itemTemplate: function (value) {
            if (typeof value != 'object' || !value) return '';
            var result = value[this.textField];
            return result === undefined || result === null ? '' : '' + result;
        },

        editTemplate: function (value) {
            if (!this.editing)
                return this.itemTemplate(value);

            var $result = this.editControl = this._createSelect();
            (typeof value === 'object') && $result.val(value[this.valueField]);
            return $result;
        },

        filterTemplate: function () {
            if (!this.filtering)
                return "";

            var grid = this._grid,
                valueField = this.valueField,
                $result = this.filterControl = this._createSelect(),
                isNoFilterPresent = false;
            $.each(this.items, function (index, item) {
                var value = valueField ? item[valueField] : index;
                if (!value || value == '0' || value == '')
                    isNoFilterPresent = true;
            });
            if (!isNoFilterPresent) {
                $result.prepend('<option value="0" selected=true>&nbsp;</option>');
            }
            if (this.autosearch) {
                $result.on("change", function (e) {
                    grid.search();
                });
            }

            return $result;
        },

        filterValue: function () {
            var val = this.filterControl.val();
            return this.convertStoredValue(val);
        },

        insertValue: function () {
            var val = this.insertControl.val();
            return this.convertStoredValue(val);
        },

        editValue: function () {
            var val = this.editControl.val();
            return this.convertStoredValue(val);
        }

    });

    jsGrid.fields.dictionary = JsDictionaryField;
    //End of support of dictionary fields

    /*****************************************************************************
     * Functions to support additional dictionaries, for example,
     *  when the Project has University object, it requires this support
     *
     */
    var domainMap = {},
        webUtils = function () {},
        filterFunctions = {
            text: function (data, filter) {
                if (typeof filter != 'string') return false;
                if (typeof data != 'string') return true;
                data = data.toUpperCase();
                filter = filter.toUpperCase();
                if (data.indexOf(filter) < 0) return true;
                return false;
            },
            dictionary: function (data, filter, field) {
                var valueField = field.valueField ? field.valueField : 'id',
                    filterValue = typeof filter == 'object' ? filter[valueField] : filter,
                    dataValue = typeof data == 'object' && data != null ? data[valueField] : data;
                if (!filterValue || filterValue === '0' || filterValue === '') return false;
                if (!dataValue || dataValue != filterValue) return true;
                return false;
            },
            select: function (data, filter, field) {
                var valueField = field.valueField ? field.valueField : 'id',
                    filterValue = typeof filter == 'object' ? filter[valueField] : filter,
                    dataValue = typeof data == 'object' && data != null ? data[valueField] : data;
                if (!filterValue || filterValue === '0' || filterValue === '') return false;
                if (!dataValue || dataValue != filterValue) return true;
                return false;
            },
            jsDate: function (data, filter) {
                if (typeof filter !== 'string') return false;
                var p = filter.indexOf('/'), datFrom, datTo;
                if (p < 0) return false;
                datFrom = filter.substr(0, p).trim();
                datTo = filter.substr(p + 1).trim();
                if (datFrom === '') datFrom = false;
                else datFrom = new Date(datFrom);
                if (datTo === '') datTo = false;
                else datTo = new Date(datTo);
                if (!datFrom && !datTo) return false;
                if ((typeof data != 'string' || data.trim() === '')&&
                    (typeof data != 'number' || data===0)) return true;
                data = new Date(data);
                if (datFrom && data < datFrom) return true;
                if (datTo && data > datTo) return true;

                return false;
            }
        },
        getOrApply = function (value, context) {
            if ($.isFunction(value)) {
                return value.apply(context, $.makeArray(arguments).slice(2));
            }
            return value;
        },
        jsgridFilterOut = function (data, filter, fields) {
            var newData = [], isFiltered, name, fn, valDat, valFilter;
            if (!data || typeof data != 'object') return newData;
            if (!Array.isArray(data)) data = [data];
            if (!filter || typeof filter != 'object' || !fields) return data;
            if (!Array.isArray(fields)) {
                if (Array.isArray(fields.fields)) fields = fields.fields;
                else return data;
            }
            data.forEach(function (record) {
                isFiltered = false;
                fields.forEach(function (field) {
                    name = field.name;
                    fn = filterFunctions[field.type];
                    if (typeof name == 'string' && name !== '' && typeof fn === 'function' &&
                        typeof filter[name] != 'undefined' && filter[name] !== null) {
                        valDat = record[name];
                        valFilter = filter[name];
                        if (typeof valFilter == 'string') {
                            valFilter = valFilter.trim();
                            if (valFilter === '') return;
                        }
                        if (fn(valDat, valFilter, field)) {
                            isFiltered = true;
                            return false;
                        }
                    }
                });
                if (!isFiltered) newData.push(record);
            });
            return newData;
        };

    jsGrid.Grid.prototype.deleteItem = function (item) {
        var me = this,
            $row = me.rowByItem(item);

        if (!$row.length)
            return;

        if (this.confirmDeleting) {
            var message = getOrApply(me.deleteConfirm, me, $row.data("JSGridItem"));
            WebUtils.confirm(function (confirmed) {
                if (confirmed) me._deleteRow($row);
            }, message);
        } else {
            return me_deleteRow($row);
        }
    };
    jsGrid.Grid.prototype.loadData = function (filter) {
        filter = filter || (this.filtering ? this.getFilter() : {});

        $.extend(filter, this._loadStrategy.loadParams(), this._sortingParams());

        var args = this._callEventHandler(this.onDataLoading, {
            filter: filter
        });

        return this._controllerCall("loadData", filter, args.cancel, function (loadedData) {
            if (!loadedData)
                return;
            loadedData = jsgridFilterOut(loadedData, filter, this.fields);
            this._loadStrategy.finishLoad(loadedData);

            this._callEventHandler(this.onDataLoaded, {
                data: loadedData
            });
        });
    };


    webUtils.prototype = {
        readFromSelect: function (id, isStringValue, textField, valueField) {
            var res = [];
            if (!valueField) valueField = 'id';
            if (!textField) textField = 'title';
            $('#' + id).find('option').each(function () {
                var obj = {}, option = $(this);
                obj[valueField] = option.attr('value');
                if (!isStringValue) obj[valueField] = parseInt(obj[valueField]);
                obj[textField] = option.text();
                res.push(obj);
            });
            this.saveListByDomain(id, valueField, res);
            return res;
        },


        getListAsMap: function (list, valueField) {
            var map = {};
            if (Array.isArray(list)) {
                list.forEach(function (item) {
                    map[item[valueField]] = item;
                });
            }
            return map;
        },

        getItemByDomainAndId: function (domainId, id) {
            if (!domainMap[domainId]) return null;
            return domainMap[domainId].map[id];
        },

        saveListByDomain: function (domainId, valueField, list) {
            domainMap[domainId] = {
                valueField: valueField,
                list: list,
                map: this.getListAsMap(list, valueField)
            }
        },

        getMapByDomainId: function (domainId) {
            return domainMap[domainId].map;
        },

        show: function (message, title) {
            if (!title)
                title = 'Error';

            if (!message)
                message = 'Sorry for the server error';

            $("<div></div>").html(message).dialog({
                title: title,
                resizable: false,
                modal: true,
                open: function () {
                    var el = $(this);
                    el.parent().css('zIndex', 10000);
                    return this;
                },
                buttons: {
                    "Ok": function () {
                        $(this).dialog("close");
                    }
                }
            });
        },

        confirm: function (callback, message, buttons, title) {
            if (!title)
                title = 'Confirm';

            if (!message)
                message = 'Please, confirm';

            if (typeof callback != 'function') {
                console.warn('Error in calling confirm, the first argument must be a function');
                return;
            }
            if (!Array.isArray(buttons)) buttons = [];
            if (buttons.length < 1) {
                buttons[0] = 'Yes';
            }
            if (buttons.length < 2) {
                buttons[1] = 'Cancel';
            }
            var buttonsFn = {};
            buttonsFn[buttons[0]] = function () {
                $(this).dialog("close");
                callback(true);
            };
            buttonsFn[buttons[1]] = function () {
                $(this).dialog("close");
                callback(false);
            };
            $("<div></div>").html(message).dialog({
                title: title,
                resizable: false,
                modal: true,
                open: function () {
                    var el = $(this);
                    el.parent().css('zIndex', 10000);
                    return this;
                },
                buttons: buttonsFn
            });
        }
    };
    window.WebUtils = new webUtils();
})();
