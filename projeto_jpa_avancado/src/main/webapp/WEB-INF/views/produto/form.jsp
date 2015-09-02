<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<c:import url="../template/top.jsp" />
<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading">Novo produto</div>

		<div class="panel-body">
			<form action="${spring:mvcUrl('cadastraProduto').build()}" method="post">
				<div class="form-group">
					<label for="nome">
						Nome:
					</label>
					<input type="text" class="form-control" name="nome" value="${produto.nome}">
					<form:errors path="produto.nome" />
				</div>

				<div class="form-group">
					<label for="nome">
						Link da foto:
					</label>
					<input type="text" class="form-control" name="linkDaFoto" value="${produto.linkDaFoto}">
					<form:errors path="produto.linkDaFoto" />
				</div>
				<div class="form-group">
					<label for="nome">
						Preço:
					</label>
					<input type="text" class="form-control" name="preco" value="${produto.preco}">
					<form:errors path="produto.preco" />
				</div>
				<div class="form-group">
					<label for="nome">
						Loja:
					</label>
					<select name="loja.id" class="form-control">
						<option value="selecione">Selecione</option>
						<c:forEach items="${lojas}" var="loja">
							<option value="${loja.id}">${loja.nome}</option>
						</c:forEach>
					</select>
					<form:errors path="produto.loja.id" />
				</div>
				<div class="form-group">
					<label for="descricao">
						Descrição:
					</label>
					<textarea name="descricao" class="form-control">${produto.descricao}</textarea>
				</div>
				<form:errors path="produto.descricao" />
				<div class="form-group">
					<input type="submit" class="btn btn-success" value="Cadastrar">
				</div>
				
			</form>
		</div>
	</div>
</div>


</body>
</html>