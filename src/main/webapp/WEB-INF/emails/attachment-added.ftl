<#include "header.ftl">
  <p>File attachment <em>${attachment.text}</em> added to <#if attachment.team??>team <em>${team.title}</em> of</#if>project <em>${project.title}</em>:${attachment.link}.</p>
<#include "footer.ftl">
