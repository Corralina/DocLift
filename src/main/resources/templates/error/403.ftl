<#import "../parts/dom.ftl" as dom>

<@dom.dom>
    <h1 class="justify-content text-success"> ${succes?ifExists} </h1>
    <h1 class="justify-content text-danger">Відмовлено у доступі! Недостатньо прав</h1>
    <a href="/">
        <div class="row p-3 mt-2 my_filter_button" >
            <button class="btn btn-primary my_button"  type="submit" >Повернутись до меню</button>
        </div>
    </a>



</@dom.dom>