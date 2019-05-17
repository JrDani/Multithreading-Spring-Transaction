<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Hello</title>
	<link rel="stylesheet" type="text/css" href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" />	
</head>
<body>
   <main class="container">
    <h2 class="hello-title">Faça upload de suas faturas </h2>
   	
   	<c:if test="${ message }">
   		<div class="alert alert-success">${ message }</div>
   	</c:if>
   	
   	<form name="upload" enctype="multipart/form-data" action="/" method="POST">
   		<h3>Prepare os arquivos</h3>
   		<div class="w-50 custom-file">
   			<label class="custom-file-label" for="fileInput">Escolha o arquivo..</label>
   			<input id="fileInput" type="file" name="file" multiple="true"
   			class="custom-file-input">	            
   		</div>	   		
	    	
	   	<div>
	   		<input type="submit" value="Preparar arquivos" class="btn btn-primary mt-3">
	   	</div> 	
   	</form>
   	
   	<ul class="mt-5 list-group list-group-flush">
   		<h3>Enviar arquivos via Thread</h3>		
   		<c:forEach var="file" items="${files }">
			<li class="list-group-item">
				${file}
			</li>
		</c:forEach>
	</ul>
	<a href='${s:mvcUrl("HC#persist").build()}' class="mt-3 btn btn-success">Enviar arquivos</a>
   	
   </main>  
</body>
</html>