<!DOCTYPE html>
<html lang="en">
    <head>
        <title>HTML5 drag'n'drop file upload with Servlet</title>
        <script>
            window.onload = function() {
                var dropbox = document.getElementById("dropbox");
                dropbox.addEventListener("dragenter", noop, false);
                dropbox.addEventListener("dragexit", noop, false);
                dropbox.addEventListener("dragover", noop, false);
                dropbox.addEventListener("drop", dropUpload, false);
            }

            function noop(event) {
                event.stopPropagation();
                event.preventDefault();
            }

            function dropUpload(event) {
                noop(event);
                var files = event.dataTransfer.files;
                var formData = new FormData();
                for (var i = 0; i < files.length; i++) {
                    formData.append("file" + i, files[i]);
                 }
                upload(formData);
            }

            function upload(formData) {

                var xhr = new XMLHttpRequest();
                xhr.upload.addEventListener("progress", uploadProgress, false);
                xhr.addEventListener("load", uploadComplete, false);
                xhr.open("POST", "uploader", true); // If async=false, then you'll miss progress bar support.
                xhr.send(formData);
            }

            function dropUpload2(event) {
                noop(event);
                var files = event.dataTransfer.files;

                for (var i = 0; i < files.length; i++) {
                    upload2(files[i]);
                }
            }

            function upload2(file) {
                document.getElementById("status").innerHTML = "Uploading " + file.name;

                var formData = new FormData();
                formData.append("file", file);

                var xhr = new XMLHttpRequest();
                xhr.upload.addEventListener("progress", uploadProgress, false);
                xhr.addEventListener("load", uploadComplete, false);
                xhr.open("POST", "uploader", true); // If async=false, then you'll miss progress bar support.
                xhr.send(formData);
            }

            function uploadProgress(event) {
                // Note: doesn't work with async=false.
                var progress = Math.round(event.loaded / event.total * 100);
                document.getElementById("status").innerHTML = "Progress " + progress + "%";
            }

            function uploadComplete(event) {
                document.getElementById("status").innerHTML = event.target.responseText;
            }
        </script>
        <style>
            #dropbox {
                width: 300px;
                height: 200px;
                border: 1px solid gray;
                border-radius: 5px;
                padding: 5px;
                color: gray;
            }
        </style>
    </head>
    <body>
        <center>
            <div id="dropbox">Resume Uploader for drag and drop...</div>
            <div id="status"></div>

            <h3>Resume Uploader ...</h3>
            <div class="upload">
                <form method="post" action="${pageContext.request.contextPath }/uploader" enctype="multipart/form-data">
                    <table border="0" cellpadding="2" cellspacing="2">
                        <tr>
                            <td valign="top">Resumes</td>
                            <td><input type="file" name="file" multiple="multiple" /></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><input type="submit" value="Upload" /></td>
                        </tr>
                    </table>
                </form>
             </div>
        </center>
    </body>
</html>