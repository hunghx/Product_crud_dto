<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hung1
  Date: 11/18/2023
  Time: 7:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>
<body>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script>

</script>
<script>
    const handleEdit= async (id)=> {
        // thực hiện call api
        // fetch(`http://localhost:8080/api/v1/todo/`+id).then(
        //     res=>res.json()
        // ).then(data => {
        //     document.getElementById("idEdit").value = data.id;
        //     document.getElementById("taskEdit").value = data.task;
        //     document.getElementById("statusEdit").value = data.status;
        // })

        // ajax
        // console.log({"hehe":$});
        // $.ajax({
        //     url: `http://localhost:8080/api/v1/todo/`+id,
        //     error: function(e) {
        //         console.log("err",e)
        //     },
        //     dataType: 'json',
        //     success: function(data) {
        //         console.log(data)
        //         $("#idEdit").val(data.id);
        //         $("#taskEdit").text(data.task);
        //         $("#statusEdit").val(data.status.toString());
        //     },
        //     type: 'GET'
        // });

// axios

        const {data} = await axios.get(`http://localhost:8080/api/v1/todo/`+id);
        document.getElementById("idEdit").value = data.id;
            document.getElementById("taskEdit").value = data.task;
            document.getElementById("statusEdit").value = data.status;

    }
</script>
<h1>Danh sách công việc</h1>
<button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#modal-add">
    Thêm mới
</button>
<table border="10" cellpadding="20" cellspacing="10">
    <tr>
        <th>STT</th>
        <th>Công việc</th>
        <th>Trạng thái</th>
        <th colspan="2">Thao tác</th>
    </tr
    <c:forEach items="${list}" var="todo" varStatus="loop">
        <tr>
            <td>${loop.count}</td>
            <td>${todo.task}</td>
            <td>${todo.status?"đã hoàn thành":"chưa hoàn thành"}</td>
            <td><button onclick="handleEdit(${todo.id})" type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#modal-edit">
                edit
            </button></td>
            <td><a href="/todo/${todo.id}" onclick="return confirm('Ban có chắc muốn xóa không')">Delete</a></td>
        </tr>
    </c:forEach>
</table>

<%-- Modal add --%>
<div class="modal fade" id="modal-add" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="/todo" method="post">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="mb-3">
                    <label for="id" class="form-label">ID</label>
                    <input type="number" class="form-control" name="id" id="id" >
                </div>

                <div class="mb-3">
                    <label for="task" class="form-label">Task</label>
                    <textarea class="form-control" id="task" name="task" rows="3"></textarea>
                </div>
                <div class="mb-3">
                    <label for="status" class="form-label">Status</label>
                    <select name="status" id="status">
                        <option value="true">Hoàn thành</option>
                        <option value="false">Chưa hoàn thành</option>
                    </select>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-primary">Add</button>
            </div>
            </form>
        </div>
    </div>
</div>
<%-- Modal edit --%>
<div class="modal fade" id="modal-edit" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="/todo/update" method="post">
            <div class="modal-header">
                <h1 class="modal-title fs-5" >Modal title</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="mb-3">
                    <label for="idEdit" class="form-label">ID</label>
                    <input type="number" class="form-control" name="id" id="idEdit" >
                </div>

                <div class="mb-3">
                    <label for="taskEdit" class="form-label">Task</label>
                    <textarea class="form-control" id="taskEdit" name="task" rows="3"></textarea>
                </div>
                <div class="mb-3">
                    <label for="statusEdit" class="form-label">Status</label>
                    <select name="status" id="statusEdit">
                        <option value="true">Hoàn thành</option>
                        <option value="false">Chưa hoàn thành</option>
                    </select>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-primary">Add</button>
            </div>
            </form>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</body>
</html>
