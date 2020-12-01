<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>file</title>
</head>
<body>
<div>
    <form action="${pageContext.request.contextPath}/upload" enctype="multipart/form-data" method="post">
        <input type="file" name="file"/>
        <input type="submit" value="upload">
    </form>

    <hr>

    <form action="${pageContext.request.contextPath}/fileImgSave" method="post" enctype="multipart/form-data">
        <input type="file" name="filename" multiple="multiple" value="">
        <input type="file" name="filename" multiple="multiple" value="">
        <input type="file" name="filename" multiple="multiple" value="">
        <input type="submit" value="上传">
    </form>
</div>
</body>
</html>
