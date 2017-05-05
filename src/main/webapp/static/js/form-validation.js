jQuery.validator.addMethod("checkSize", function(val, element) {

	var size = element.files[0].size;

	if (size > 1048576)
		return false;
	else
		return true;
}, "File size should be less than 1MB");

$(document).ready(function() {

	$("#message").show();
	setTimeout(function() {
		$("#message").hide();
	}, 5000);

	if ($('#mode').val() == "edit") {
		$("#myform").rules("remove", "remote");
	}

	$('#myform').validate({
		rules : {
			title : {
				required : true,
				remote : function() {
					var r = {
						url : $('#getTitleUrl').val(),
						type : "POST",
						contentType : "application/json; charset=utf-8",
						dataType : "json",
						data : '{"title": "' + $("#title").val() + '"}'
					}
					return r;
				}
			},
			author : "required",
			comment : "required"
		},
		messages : {
			title : {
				required : " Please specify title",
				remote : " Title already exists. Please choose different name."
			},
			author : " Please specify author",
			comment : " Please enter comments"
		},

		submitHandler : function() {

			$('#progress').show();
			form.submit();
		}
	});

	$('#file').rules("add", {
		required : true,
		extension : "txt|doc|docx|pdf",
		checkSize : true,
		messages : {
			required : " Please select the file",
			extension : " Only txt|doc|docx|pdf extention file is allowed"
		}
	});

	$('#image').rules("add", {
		required : true,
		extension : "jpg|png",
		checkSize : true,
		messages : {
			required : " Please select the image",
			extension : " Only jpg|png extention image is allowed"
		}
	});

	$('#editFile').change(function() {

		$('#editFile').rules("add", {
			required : false,
			extension : "txt|doc|docx|pdf",
			checkSize : true,
			messages : {
				extension : " Only txt|doc|docx|pdf extention file is allowed"
			}
		});

		$('#uploadFile').val($(this).val().replace(/C:\\fakepath\\/i, ''));

	});

	$('#editImage').change(function() {

		$('#editImage').rules("add", {
			required : false,
			extension : "jpg|png",
			checkSize : true,
			messages : {
				extension : " Only jpg|png extention image is allowed"
			}
		});

		$('#uploadImage').val($(this).val().replace(/C:\\fakepath\\/i, ''));
	});

});

function deleteArticle(id) {

	if (confirm("Are you sure want to delete article?")) {

		var data = {
			"id" : id
		}

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : $('#deleteArticleUrl').val(),
			data : JSON.stringify(data),
			dataType : 'json',
			success : function(response) {

				$.ajax({
					type : "GET",
					url : $('#getArticleUrl').val(),
					success : function(response) {
						window.location.reload();
					},
					error : function(e) {
						$('#errorMsg').html(response.message);
					}
				}).submit();
			},
			error : function(e) {
				$('#errorMsg').html(response.message);
			}
		});
	}
}

$("#tg tr").css("background-color", function(index) {
	return index % 2 == 0 ? "blue" : "red";
});

$(function() {
	var token = $("input[name='_csrf']").val();
	var header = "X-CSRF-TOKEN";
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
});
