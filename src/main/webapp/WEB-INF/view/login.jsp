<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>


<section>

    <div>

            <form action="<c:url value="/do/login"/>" method="post">
                <input type="hidden" name="command1" value="login55" />
                <input type="text" name="login" value="" /><br/>
                <input type="text" name="password" value="" /><br/>
                <input type="submit" value="Нажать" /><br/>
                ${wrongAction}
                <br/>

            </form>



    </div>

    </div>
</section>


</body>
</html>
