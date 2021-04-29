<#import "../parts/dom.ftl" as dom>
<@dom.dom>

    <div class="mt-5 shadow-lg mb-5 bg-white rounded d-flex  justify-content-center" style="height: 70vh">
        <div class="d-flex flex-column  align-items-center justify-content-center" style="width: 60%; position: relative">
            <div class="mb-3">
                <h1 class="justify-content"> Завантаження документу </h1>
            </div>
            <div class="err" style="position: absolute; top: 20px; left: 15px; color: red">
                <#if error??>
                    <p>* ${error?ifExists}</p>
                </#if>
            </div>
            <form action="/document" method="post" enctype="multipart/form-data" style="min-width: 50vw" class="px-4 py-3">

                <div class="form-group mt-4">
                    <label for="exampleDropdownFormEmail1">Номер документу</label>
                    <input type="text"  name="number" value="${number?ifExists}" class="form-control" id="exampleDropdownFormEmail1" placeholder="номер">
                </div>
                <div class="form-group mt-3">
                    <label for="exampleDropdownFormPassword1">Коментар до документу</label>
                    <input name="coment" value="${coment?ifExists}" type="text" class="form-control" id="exampleDropdownFormPassword1" placeholder="коментар">
                </div>

                <div class="form-group mt-3">
<#--                    <div class="rd">-->
<#--                        <label class="m-3">Image  <input class="my_file_upload" type="file" name="image" ></label>-->
<#--                    </div>-->
                    <div class="rd">
                        <label class="m-3">File  <input class="my_file_upload" type="file" name="file" onchange="attach(this)"/></label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn btn-primary my_button mt-3" type="submit" >Зберегти</button>
            </form>
        </div>
    </div>

    <script>
        function attach(input){

            var attach_size = 15*1024*1024; //15мб

            var attach_file = input.files[0];

            if(attach_file.size > attach_size){

                $('.err').html('Размер вложений ограничен 15 мб!');

                $('.my_file_upload').val(''); //удалить аттач, если превышен размер файла

            }

        };

    </script>

</@dom.dom>