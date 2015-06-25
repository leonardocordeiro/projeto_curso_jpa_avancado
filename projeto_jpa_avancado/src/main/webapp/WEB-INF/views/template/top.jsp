<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
		<meta charset="UTF-8"></meta>
	</head>
	<body>
		<nav class="navbar navbar-inverse" role="navigation">
			<div class="navbar-header">
				<a class="navbar-brand" href="<c:url value='/' />">Home</a>
			</div>
			<form class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<select class="form-control">
						<option>Lojas</option>
						<c:forEach items="${lojas}" var="loja">
							<option>${loja.nome}</option>
						</c:forEach>
					</select>
				</div>
				<button type="submit" class="btn btn-default">Pesquisar</button>
			</form>
		</nav>
			