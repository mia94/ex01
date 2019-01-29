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
						<form action="modifyPage" method="post">
							<div class="box-body">
								<input type="hidden" name="bno" value="${boardVO.bno }">
								<input type="hidden" name="page" value="${cri.page }">
								<input type="hidden" name="searchType" value="${cri.searchType }">
								<input type="hidden" name="keyword" value="${cri.keyword }">
								<div class="form-group">
									<label>Title</label>
									<input type="text" name="title" class="form-control" value="${boardVO.title }">
								</div>
								<div class="form-group">
									<label>Content</label>
									<textarea rows="5" class="form-control" name="content">${boardVO.content }</textarea>
								</div>
								<div class="form-group"> 
									<label>Writer</label>
									<input type="text" name="writer" class="form-control" value="${boardVO.writer }" readonly="readonly">
								</div>
							</div>
							<div class="box-footer">
								<button type="submit" class="btn btn-warning" id="btnModify">Modify</button>
							</div>
						</form>

					</div>
				</div>
			</div>
		</div>
	</section>
	
	<script>

	</script>
	
	
<%@ include file="../include/footer.jsp" %>