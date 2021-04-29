<#include "security.ftl">
<#import "autentification_form.ftl" as au_f>

<nav id="navBar" class="navbar navbar-expand-lg navbar-light bg-light rounded navbar-toggleable-md justify-content-between">
    <a class="navbar-brand" id="" href="/"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav">
            <#if isLog>

                <li class="nav-item">
                    <a class="nav-link" id="" href="/resolutionList" title="Список моїх резолюцій"><img src="/static/text-document-check.svg" alt=""></a>
                </li>

                <#if isRecorted || isAdmin>

                    <li class="nav-item">
                        <a class="nav-link" id="list_users" href="/allResolutionList" title="Список користувачів"><img src="/static/adminList.svg" alt=""></a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" id="add_doc" href="/createDocument" title="Додати документ"><img src="/static/text-document-add.svg" alt=""></a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" id="add_doc" href="/documentArchive" title="Архів документів"><img src="/static/archive.svg" alt=""></a>
                    </li>

                </#if>

                <#if isTablin || isConfirm || isAdmin>

                    <li class="nav-item">
                        <a class="nav-link" id="" href="/resolutionList/status" title="Список непогоджених резолюцій"><img src="/static/edit-document.svg" alt=""></a>
                    </li>

                </#if>

                <#if isAdmin || isTablin>

                <li class="nav-item">
                    <a class="nav-link" id="list_docs" href="/documentList" title="Список документів"><img src="/static/text-documents-line.svg" alt=""></a>
                </li>

                </#if>

                <#if isAdmin>

                    <li class="nav-item">
                        <a class="nav-link" id="list_users" href="/userList" title="Список користувачів"><img src="/static/group.svg" alt=""></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="user_reg" href="/registration" title="Реєстрація користувача"><img src="/static/wondicon-ui-free-add-user_111248.svg" alt=""></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="user_reg" href="/userSending" title="Зміна користувача"><img src="/static/telegramSetting.svg" alt=""></a>
                    </li>

                </#if>

            </#if>


        </ul>

        <#if isLog>
            <div class="navbar-text" style="color: #ede0b6; margin-right: 15px">${name} </div>
            <div style="margin-left: 15px"><@au_f.logaut></@au_f.logaut></div>
        </#if>


    </div>
</nav>