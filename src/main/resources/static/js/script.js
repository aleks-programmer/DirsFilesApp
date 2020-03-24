$(document).ready(function () {
    if ($('#dirsTable tbody').length) {
        $.ajax({
            url: "dirs",
            type: 'GET',
            success: function (data) {
                $('#dirsTable tbody').html(getDirsContent(data));
            },
            error: function (data) {
                var responseObject = data.responseJSON;
                if (data.status === 500) {
                    $("#errorModal .modal-title").html('Внутренняя ошибка сервера');
                    $("#errorModal .modal-body p").html(responseObject.message);
                    $('#errorModal').modal('show');
                }
            }
        });
    }

    $("#addToListButton").click(function () {
        var newDirPath = $(".newDir input#newDir")[0].value;
        $.ajax({
            url: "addDirToList",
            type: 'POST',
            data: newDirPath,
            success: function (data) {
                $('#dirsTable tbody').append(getDirContent(data));
            },
            error: function (data) {
                var responseObject = data.responseJSON;
                if (data.status === 500) {
                    $("#errorModal .modal-title").html('Внутренняя ошибка сервера');
                    $("#errorModal .modal-body p").html(responseObject.message);
                    $('#errorModal').modal('show');
                }

            },
            contentType: "application/json"
        });
    });

    $(document).on('click', '#filesButton', function(){
        var closestTr = this.closest('tr');
        $.ajax({
            url: "files",
            type: 'GET',
            data: {
                dirId: closestTr.id
            },
            success: function (dirFiles) {
                if (dirFiles !== null || dirFiles !== undefined) {
                    var closestTrJQ = $(closestTr);
                    $("#filesModal .modal-title").html(closestTrJQ.find('#path')[0].innerText
                        + ' ' + closestTrJQ.find('#created')[0].innerText);
                    $("#filesModal .modal-body #dirFilesTable tbody").html(getDirFilesContent(dirFiles));
                    $('#filesModal').modal('show');
                }
            },
            error: function (data) {
                var responseObject = data.responseJSON;
                if (data.status === 500) {
                    $("#errorModal .modal-title").html('Внутренняя ошибка сервера');
                    $("#errorModal .modal-body p").html(responseObject.message);
                    $('#errorModal').modal('show');
                }

            },
            contentType: 'application/json'
        });
    });

    var getDirsContent = function (dirs) {
        var content = '';
        dirs.forEach(function (item) {
            content += getDirContent(item);
        });

        return content;
    };

    var getDirFilesContent = function (dirFiles) {
        var content = '';
        dirFiles.forEach(function (item) {
            var size = item.dir === true ? '&lt;DIR&gt;' : getSize(item.dirFileSize);
            content += "<tr>" +
                "<td>" + item.dirFileName + "</td>" +
                "<td>" + size + "</td>" +
                "</tr>";
        });

        return content;
    };

    var getDirContent = function (item) {
        var created = new Date(item.created);
        return "<tr id='" + item.id + "'>" +
            "<td id='created'>" + created.toLocaleDateString('ru-RU', {
                day: '2-digit',
                month: '2-digit',
                year: 'numeric'
            }) + ' ' +
            created.toLocaleTimeString('ru-RU', {
                hour: '2-digit',
                minute: '2-digit',
                second: '2-digit'
            }) + "</td>" +
            "<td id='path'>" + item.path + "</td>" +
            "<td id='dirCount'>" + item.dirCount + "</td>" +
            "<td id='fileCount'>" + item.fileCount + "</td>" +

            "<td id='totalFileSize'>" + getSize(item.totalFileSize) + "</td>" +
            "<td id='files'><button id=\"filesButton\">Файлы</button></td>" +
            "</tr>";
    };

    var getSize = function (bytes) {
        var i = 0;
        var total = bytes;
        var prev = bytes;

        while (total >= 1) {
            prev = total;
            total /= 1024;
            i++;
        }

        if (i === 0) {
            return prev + 'b'
        } else if (i === 1) {
            return prev + 'b';
        } else if (i === 2) {
            return prev.toFixed(2) + 'Kb';
        } else if (i === 3) {
            return prev.toFixed(2) + 'Mb';
        } else {
            return prev.toFixed(2) + 'Gb';
        }
    };
});