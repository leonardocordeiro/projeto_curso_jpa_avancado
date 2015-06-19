<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
		<meta charset="UTF-8"></meta>
	</head>
	<body>
		<nav class="navbar navbar-inverse" role="navigation">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Home</a>
			</div>				
		</nav>
			<div class="container">
				<div class="col-sm-8">
					<div class="panel panel-default">
						<div class="panel-heading">
							Produtos
						</div>
						<div class="panel-body">
							<div class="container">
								<h3>${produto.nome}</h3>
								<legend></legend>
								<img src="${produto.linkDaFoto}" width="200" height="300">
								
							</div>									
						</div>	<!-- fim panel_body -->
					</div>
				</div>
				<div class="col-sm-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							Buscar por:
						</div>
						<div class="panel-body"> 
							<form class="form-group">
								<p>
									<input type="text" class="form-control" placeholder="Nome">
								</p>
								<p>
									<select class="form-control">
										<option selected>Categoria</option>	
										<option>Tecnologia</option>	
										<option>MÃºsica</option>	
										<option>Moda</option>
									</select>
								</p>
								<p>
									<select class="form-control">
										<option selected>Loja</option>	
										<option>Loja 1</option>	
										<option>Loja 2</option>	
										<option>Loja 3</option>
									</select>
								</p>
								<p>
									<button class="btn btn-primary form-control">Buscar</button>
								</p>
							</form>
						</div>
					</div>					
			</div>
	</body>
</html>