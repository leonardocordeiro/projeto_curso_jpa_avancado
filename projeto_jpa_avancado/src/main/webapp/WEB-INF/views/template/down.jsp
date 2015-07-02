<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

</div>
</div>
<div class="col-sm-4">
	<div class="panel panel-default">
		<div class="panel-heading">Buscar por:</div>
		<div class="panel-body">
			<form class="form-group" method="post" action="<c:url value="/produtos"/>">
				<p>
					<input type="text" name="nome" class="form-control"
						placeholder="Nome">
				</p>
				<p>
					<select class="form-control" name="categoria">
						<option selected value="">Categoria</option>
						<c:forEach items="${categorias}" var="categoria">
							<option value="${categoria.nome}">${categoria.nome}</option>
						</c:forEach>
					</select>
				</p>
				<p>
					<select class="form-control" name="loja">
						<option selected value="">Loja</option>
						<c:forEach items="${lojas}" var="loja">
							<option value="${loja.id}">${loja.nome}</option>
						</c:forEach>
					</select>
				</p>
				<p>
					<input type="submit" class="btn btn-primary form-control"
						value="Buscar">
				</p>
			</form>
		</div>
	</div>
</div>
</body>
</html>