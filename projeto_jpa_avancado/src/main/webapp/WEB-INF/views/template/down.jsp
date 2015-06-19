<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

</div>
</div>
<div class="col-sm-4">
	<div class="panel panel-default">
		<div class="panel-heading">Buscar por:</div>
		<div class="panel-body">
			<form class="form-group" method="post" action="produtos">
				<p>
					<input type="text" name="nome" class="form-control"
						placeholder="Nome">
				</p>
				<p>
					<select class="form-control" name="categoria">
						<option selected value="">Categoria</option>
						<option value="tecnologia">Tecnologia</option>
						<option value="musica">Música</option>
						<option value="moda">Moda</option>
					</select>
				</p>
				<p>
					<select class="form-control" name="loja">
						<option selected value="">Loja</option>
						<option value="lalala">Loja 1</option>
						<option>Loja 2</option>
						<option>Loja 3</option>
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