<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:import url="template/top.jsp" />
<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading">Novo produto</div>

		<div class="panel-body">
			<fieldset>
				<legend>Novo produto</legend>
			</fieldset>
			
			<form action="produto" method="post">
				<div class="form-group">
					<label for="nome">
						Nome:
					</label>
					<input type="text" class="form-control" name="nome">
				</div>

				<div class="form-group">
					<label for="nome">
						Link da foto:
					</label>
					<input type="text" class="form-control" name="linkDaFoto">
				</div>
					
						<div class="form-group">
							<label for="nome">
								Loja:
							</label>
							<select name="loja.nome" class="form-control">
								<option>Selecione</option>
								<c:forEach items="${lojas}" var="loja">
									<option value="${loja.nome}">${loja.nome}</option>
								</c:forEach>
							</select>
						</div>
				<div class="form-group">
					<input type="submit" class="btn btn-success" value="Cadastrar">
				</div>
				
			</form>
		</div>
	</div>
</div>


</body>
</html>