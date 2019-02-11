<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
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
								<input type="hidden" name="searchType" value="${cri.searchType }">
								<input type="hidden" name="keyword" value="${cri.keyword }">
							</form>
							
					</div>
				</div>
			</div>
		</div>
		
		<!-- 댓글 -->
		<div class="row">
			<div class="col-xs-12">
				<div class="box box-success">
					<div class="box-header">
						<h3 class="box-title">ADD NEW REPLY</h3>
					</div>
					<div class="box-body">
						<label>Writer</label>
						<input type="text" class="form-control" placeholder="User Id" id="newReplyWriter">
						<label>Reply Text</label>
						<input type="text" class="form-control" placeholder="Reply Text" id="newReplyText">
					</div>
					<div class="box-footer">
						<button class="btn btn-primary" id="btnReplyAdd">ADD REPLY</button>
					</div>
				</div>
				<ul class="timeline"><!-- 댓글이 들어갈 곳 통째로 비우면 버튼까지 사라지기 때문에 조심 -->
					<li class="time-label" id="repliesDiv">
						<span class="bg-green">Replies List</span>
					</li>
				</ul>
				<div class="text-center">
					<ul id="pagination" class="pagination pagination-sm no-margin">
					</ul>
				</div>
			</div>
		</div>
		
		<div id="modifyModal" class="modal modal-primary fade" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button><!-- close버튼 -->
					</div>
					<div class="modal-body">
						<p><input type="text" id="replytext" class="form-control"></p><!-- 내용 들어감 -->
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-info" id="btnReplyMod" data-rno="">Modify</button><!-- rno번호 this로 가져올수 있음 -->
						<button type="button" class="btn btn-default" data-dismiss="">Close</button>
					</div>
				</div>
			</div>
		</div>
		
	</section>
	
	<script id="template1" type="text/x-handlebars-template"> 
	{{#each.}}
		<li class="replyLi" data-rno="{{rno}}">
		<i class="fa fa-comments bg-blue"></i>
		<div class="timeline-item">
			<span class="item">
				<i class="fa fa-clock-o"></i>{{tempDate regdate}}
			</span>
			<h3 class="timeline-header">
				<strong>{{rno}}</strong> - {{replyer}}
			</h3>
			<div class="timeline-body">
				{{replytext}}
			</div>
			<div class="timeline-footer">
				<a class="btn btn-primary btn-xs btnModify" data-toggle="modal" data-target="#modifyModal" data-rno="{{rno}}">Modify</a>
				<a class="btn btn-danger btn-xs btnDelete">Delete</a>
			</div>
		</div>
		</li>
	{{/each}}
	</script>
	
	<script>
	Handlebars.registerHelper("tempDate", function(value){
		var date = new Date(value);
		var year = date.getFullYear();
		var month = date.getMonth()+1;
		var day = date.getDate();
		
		return year+"/"+month+"/"+day
	})
	
	var bno = ${boardVO.bno };
	
	function getPageList(page){
		$.ajax({
			url:"${pageContext.request.contextPath}/replies/"+bno+"/"+page,
			type:"get",
			dataType:"json",
			success:function(json){
				console.log(json);
				$(".replyLi").remove();//본인까지 통째로 사라지도록
			
			var source = $("#template1").html();
			var f = Handlebars.compile(source);
			var result = f(json.list);
			$(".timeline").append(result);
			
			$("#pagination").empty();
			for(var i=json.pageMaker.startPage;i<=json.pageMaker.endPage;i++){
				var liTag = $("<li>");
				var aTag = $("<a>").attr("href","#").text(i);
				if(i==json.pageMaker.cri.page){
					liTag.attr("class","active");
				}
				liTag.append(aTag);
				$("#pagination").append(liTag);
			}
			

			}
		})
	}
	</script>
	
	
	<script>
		$(function(){
			getPageList(1);
			//추가
			$("#btnReplyAdd").click(function(){
				//bno는 알아서 받아오고, replyer, replytext넘겨주기
				var replyer = $("#newReplyWriter").val();
				var replytext = $("#newReplyText").val();
				//@RequestBody를 사용했기때문에
				var jsonBody = {bno:bno, replyer:replyer, replytext:replytext};
				//@RequestBody를 사용했으면headers, JSON.stringify를 반드시 사용해야함
				$.ajax({
					url:"${pageContext.request.contextPath}/replies/",
					type:"post",
					headers:{
						"Content-Type":"application/json",
						"X-HTTP-Method-Override":"POST"
					},
					data:JSON.stringify(jsonBody),/*JSON.stringify는 {bno:bno, replyer:replyer, replytext:replytext}이런 스트링으로 변환*/
					dataType:"text",/*String으로 반환되면 객체가 아니기때문에 json이 아닌 text로 받아야함*/
					success:function(json){
						console.log(json);
						if(json=="success"){
							alert("등록하였습니다.");
							getPageList(1);
							$("#newReplyWriter").val("");
							$("#newReplyText").val("");
						}
					}
				})
			})
			//삭제
			$(document).on("click",".btnDelete",function(){
				var rno = $(this).parents(".replyLi").attr("data-rno");
				$.ajax({
					url:"${pageContext.request.contextPath}/replies/"+rno,
					type:"delete",
					dataType:"text",
					success:function(json){
						console.log(json);
						if(json == "success"){
							alert(rno+"가 삭제되었습니다.");
						}
						getPageList(1);
					}
				})
			})
			//수정
			$(document).on("click",".btnModify",function(){
				var rno = $(this).attr("data-rno");
				$("#btnReplyMod").attr("data-rno",rno);
				var text = $(this).parent().prev().text().trim();
				$("#replytext").val( jQuery.trim(text));
			})
			
		$(document).on("click","#btnReplyMod",function(){
			var rno = $(this).attr("data-rno");
			var replytext = $("#replytext").val();
			var jsonBody = {replytext:replytext};
			$.ajax({
				url:"${pageContext.request.contextPath}/replies/"+rno,
				type:"put",
				headers:{
					"Content-Type":"application/json",
					"X-HTTP-Method-Override":"PUT"
				},
				data:JSON.stringify(jsonBody),
				dataType:"text",
				success:function(json){
					console.log(json);
					if(json == "success"){
						alert(rno+"가 수정되었습니다.");
					}
					//수정완료되면 창 닫기
					$("#modifyModal").hide();
					getPageList(1);
				}
			})
		})
			
			$("#btnList").click(function(){
				//location.href="${pageContext.request.contextPath}/board/listPage";
				$("#f1").attr("action","list");
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




















