<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

	<section class="content">
		<div class="row">
			<div class="col-sm-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">REGISTER BOARD - Post</h3>
					</div>
					<form role="form" method="post" action="register">
							<div class="box-body">
								${result }
							</div>
							<div class="box-footer">
								<button type="submit" class="btn btn-primary">Submit</button>
							</div>
						</form>
				</div>
			</div>
		</div>
	</section>

<%@ include file="../include/footer.jsp" %>