<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ include file="include/header.jsp" %>

	<section class="content">
		<div class="row">
			<div class="col-sm-12">
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">Home Page</h3>
						<table class="table">
						    <thead>
						      <tr>
						        <th>Firstname</th>
						        <th>Lastname</th>
						        <th>Email</th>
						      </tr>
						    </thead>
						    <tbody>
						      <tr>
						        <td>Default</td>
						        <td>Defaultson</td>
						        <td>def@somemail.com</td>
						      </tr>      
						      <tr class="success">
						        <td>Success</td>
						        <td>Doe</td>
						        <td>john@example.com</td>
						      </tr>
						      <tr class="danger">
						        <td>Danger</td>
						        <td>Moe</td>
						        <td>mary@example.com</td>
						      </tr>
						      <tr class="info">
						        <td>Info</td>
						        <td>Dooley</td>
						        <td>july@example.com</td>
						      </tr>
						      <tr class="warning">
						        <td>Warning</td>
						        <td>Refs</td>
						        <td>bo@example.com</td>
						      </tr>
						      <tr class="active">
						        <td>Active</td>
						        <td>Activeson</td>
						        <td>act@example.com</td>
						      </tr>
						    </tbody>
					  </table>
					</div>
				</div>
			</div>
		</div>
	</section>

<%@ include file="include/footer.jsp" %>
