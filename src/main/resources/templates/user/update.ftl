<#import "../parts/dom.ftl" as dom>
<@dom.dom>

    <div class="mt-5 shadow-lg mb-5 bg-white rounded d-flex  justify-content-center" style="height: 70vh">
        <div class="d-flex flex-column  align-items-center justify-content-center" style="width: 60%; position: relative">
            <div class="mb-3">
                <h1 class="justify-content"> Оновлення списку користувачів </h1>
            </div>
            <div class="err" style="position: absolute; top: 20px; left: 15px; color: red">
                <#if error??>
                    <p>* ${error?ifExists}</p>
                </#if>
            </div>
            <form action="/userLDAP" method="post" enctype="post" style="min-width: 50vw" class="px-4 py-3">
                <div style="display: inline-block">
                    <input type="checkbox" id="mail" name="mail">
                    <label for="mail">Пошта</label>
                </div>
                <div style="display: inline-block">
                    <input type="checkbox" id="title" name="title">
                    <label for="title">Посада</label>
                </div>
                <div style="display: inline-block">
                    <input type="checkbox" id="name" name="name">
                    <label for="name">ФІО</label>
                </div>
<#--                <div style="display: inline-block">-->
<#--                    <input type="checkbox" id="phone" name="phone">-->
<#--                    <label for="phone">Телефон</label>-->
<#--                </div>-->
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn btn-primary my_button mt-3" type="submit" >Зберегти</button>
            </form>
        </div>
    </div>

</@dom.dom>