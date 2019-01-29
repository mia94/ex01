<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<section class="content">
		<div class="row">
			<div class="col-sm-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">Board List All</h3>
					</div>
					<div class="box-body">
							<div class="box-body">
								<div class="form-group">
									<label>Title</label>
									<input type="text" name="title" class="form-control" value="${boardVO.title }" readonly="readonly">
								</div>
								<div class="form-group">
									<label>Content</label>
									<textarea rows="5" class="form-control" name="content" readonly="readonly">${boardVO.content }</textarea>
								</div>
								<div class="form-group"> 
									<label>Writer</label>
									<input type="text" name="writer" class="form-control" value="${boardVO.writer }" readonly="readonly">
								</div>
							</div>
							<div class="box-footer">
								<button type="submit" class="btn btn-primary" id="btnList">GO List</button>
								<button type="submit" class="btn btn-warning" id="btnModify">Modify</button>
								<button type="submit" class="btn btn-danger" id="btnRemove">Remove</button>
							</div>
							
							<form id="f1" action="" method="post">
								<input type="hidden" name="bno" value="${boardVO.bno }">
								<input type="hidden" name="page" value="${cri.page }">
							</form>
							
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<script>
		$(function(){
			$("#btnList").click(function(){
				//location.href="${pageContext.request.contextPath}/board/listPage";
				$("#f1").attr("action","listPage");
				$("#f1").attr("method","get");
				$("#f1").submit();
			})
		})
		
		$("#btnRemove").click(function(){
			//f1(form)를 submit에 보냄
			$("#f1").attr("action","removePage");
			$("#f1").submit();
		})
		
		$("#btnModify").click(function(){
			//f1(form)를 submit에 보냄
			$("#f1").attr("action","modify");
			$("#f1").attr("method","get");
			$("#f1").submit();
		})
	</script>
	
	
<%@ include file="../include/footer.jsp" %>




















