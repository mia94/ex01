<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section class="content">
		<div class="row">
			<div class="col-sm-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">Board List All</h3>
					</div>
					<div class="box-body">
						<!-- table -->
						<table class="table table-striped">
						    <thead>
						      <tr>
						        <th style="width:20px">BNO</th>
						        <th>TITLE</th>
						        <th>WRITER</th>
						        <th>REGDATE</th>
						        <th style="width:60px">VIEWCNT</th>
						      </tr>
						    </thead>
						    <tbody>
						      <c:forEach var="item" items="${list }">
						      	<tr>
						      		<td>${item.bno }</td>
						      		<td><a href="${pageContext.request.contextPath}/board/readPage?bno=${item.bno}&page=${pageMaker.cri.page}">${item.title }</a></td>
						      		<td>${item.writer }</td>
						      		<td><fmt:formatDate value="${item.regdate }" pattern="yyyy-MM-dd HH:mm"/></td>  
						      		<td><span class="badge bg-yellow">${item.viewcnt }</span></td>
						      	</tr>
						      </c:forEach>
						    </tbody>
					  </table>
					</div>
					<div class="box-footer">
						<div class="text-center">
							<ul class="pagination">
							<c:if test="${pageMaker.prev }">
								<li><a href="${pageContext.request.contextPath}/board/listPage?page=${pageMaker.startPage-1}">&laquo;</a></li>
							</c:if>
								<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
									<li ${pageMaker.cri.page == idx ? 'class="active"': ''} ><a href="${pageContext.request.contextPath}/board/listPage?page=${idx}">${idx}</a></li>
								</c:forEach>
							<c:if test="${pageMaker.next }">
								<li><a href="${pageContext.request.contextPath}/board/listPage?page=${pageMaker.endPage+1}">&raquo;</a></li>
							</c:if>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
<%@ include file="../include/footer.jsp" %>









