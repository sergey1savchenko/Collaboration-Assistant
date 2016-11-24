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
            var urlVal = this.urlField ? (typeof value==='object' && value? value[this.urlField]: value) : '',
                url = this.urlStart + urlVal + this.urlEnd;

            return '<a href="' + url + '"><div class="js-link-field js-link-field-'+this.urlField+'"></div></a>';
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
        sorter: function (date1, date2) {
            return new Date(date1) - new Date(date2);
        },

        itemTemplate: function (value) {
            return new Date(value).toDateString();
        },

        insertTemplate: function (value) {
            return this._insertPicker = $("<input>").datepicker({defaultDate: new Date()});
        },

        editTemplate: function (value) {
            return this._editPicker = $("<input>").datepicker().datepicker("setDate", new Date(value));
        },

        insertValue: function () {
            return this._insertPicker.datepicker("getDate").toISOString();
        },

        editValue: function () {
            return this._editPicker.datepicker("getDate").toISOString();
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
        webUtils = function () {};

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
        
        show: function (message, title)
        {
            if (!title)
                title = 'Error';

            if (!message)
                message = 'Sorry for the server error';

            $("<div></div>").html(message).dialog({
                title: title,
                resizable: false,
                modal: true,
                open: function(){
                    var el = $(this);
                    el.parent().css('zIndex',10000);
                    return this;
                },
                buttons: {
                    "Ok": function()
                    {
                        $( this ).dialog( "close" );
                    }
                }
            });
        } 
    };
    window.WebUtils = new webUtils();
})();
