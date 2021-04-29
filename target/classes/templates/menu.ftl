<#import "parts/dom.ftl" as dom>

<@dom.dom>
    <h1 class="justify-content text-success"> ${succes?ifExists} </h1>
    <h1 class="justify-content text-danger"> ${error?ifExists} </h1>
    <div><img width="100%" height="80%" src="/static/logo.jpg"></div>



</@dom.dom>