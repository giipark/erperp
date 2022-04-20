/* Add here all your JS customizations */


//토스트 메시지 설정
/*
위치 설정
var toasts = [
    new Toast('error', 'toast-bottom-full-width', 'This is positioned in the bottom full width. You can also style the icon any way you like.'),
    new Toast('info', 'toast-top-full-width', 'top full width'),
    new Toast('warning', 'toast-top-left', 'This is positioned in the top left. You can also style the icon any way you like.'),
    new Toast('success', 'toast-top-right', 'top right'),
    new Toast('warning', 'toast-bottom-right', 'bottom right'),
    new Toast('error', 'toast-bottom-left', 'bottom left')
];
*/

//기본 옵션 설정
toastr.options.closeButton=true;
toastr.options.progressBar= true;
toastr.options.debug=false;
toastr.options.positionClass="toast-top-center";
toastr.options.onclick=null;
toastr.options.fadeIn=300;
toastr.options.fadeOut=1000;
toastr.options.timeOut=3000;
toastr.options.extendedTimeOut=1000;
toastr.options.preventDuplicates=true;
toastr.options.showMethod = 'slideDown'

var no=0;
/*	
/	***** 토스트 메시지 설정	
*/
function tWarning(title, content){
	toastr.remove();
	toastr.clear();
	toastr.options = {
		 "preventDuplicates": true,
		  closeButton: true,
           progressBar: true,		   
		  "debug": false,
		  "positionClass": "toast-top-center",
		  "onclick": null,
		  "fadeIn": 300,
		  "fadeOut": 1000,
		  "timeOut": 5000,
		  "extendedTimeOut": 1000,
		  showMethod : 'slideDown'
	}
	if(title==""){
	  toastr.warning(content);	
	}else{
	  toastr.warning(title, content);	
	}			
}

function tInfo(title, content){
	toastr.remove();
	toastr.clear();	
	if(title=="")toastr.info(content);	
	else toastr.info(title, content);		  	
}

function tSuccess(title, content){
	toastr.remove();
	toastr.clear();
	toastr.options = {
			 "preventDuplicates": true,
			  closeButton: true,
	           progressBar: true,		   
			  "debug": false,
			  "positionClass": "toast-top-center",
			  "onclick": null,
			  "fadeIn": 300,
			  "fadeOut": 1000,
			  "timeOut": 1500,
			  "extendedTimeOut": 1000,
			  showMethod : 'slideDown'
		}
	
	if(title=="")toastr.success(content);
	else toastr.success(title, content);		
}

function tError(title, content){
	toastr.remove();
	toastr.clear();
	if(title=="")toastr.error(content);	
	else toastr.error(title, content);			
}


function tConfirm(content){
	toastr.remove();
	toastr.clear();
	
	toastr.warning(
		"<div class='text-center'><button type='button' id='confirmationRevertYes' class='mb-xs mt-xs mr-xs btn btn-sm btn-success'>예</button>" +
		"&nbsp;&nbsp;&nbsp;<button type='button' id='confirmationRevertNo' class='mb-xs mt-xs mr-xs btn btn-sm btn-info'>아니오</button><div>" ,
		content,				
	  {
	      closeButton: false,
	      allowHtml: true,
	      onShown: function (toast) {
	          $("#confirmationRevertYes").click(function(){
	        	  
	        	 alert("ok");
	          });
	         
	          $("#confirmationRevertNo").click(function(){
	        	  
		        	 alert("no");
		      });	          
	        }
	  });
}


/**
 * 
 * 모바일 메뉴
 * @returns
 */

function navExpandedToggle(event){	
	if($(event).parent("li").is(".nav-expanded")===true){
		$(event).parent("li").removeClass("nav-expanded");
	}else{
		$(event).parent("li").addClass("nav-expanded");	
	}
}

/**
 * 
 * @param 사이드바 쿠키 설정
 * @returns
 */
function sidebarCollapsed(event){
	if($("html").hasClass("sidebar-left-collapsed") === true) {
		// 속성값이 존재함.		
		//존재해서 메뉴 open
		//쿠키 삭제 //성공시 true 반환
		var result=$.removeCookie('sidebar-left-collapsed', { path: '/' }); 
		if(result){
			
		}
	}else{
		//존재하지 않아서 close
		// 전체 사이트에 대해 365일 뒤에 만료되는 쿠키 생성
		$.cookie('sidebar-left-collapsed', 'collapsed', { expires: 365, path: '/' });		
	}
}


//천 단위 콤마
function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}



$(document).ready(function(){
	
	//숫자만
	 $(document).on("keyup", "input:text[numberOnly]", function() {$(this).val( $(this).val().replace(/[^0-9]/gi,"") );});
	 $(document).on("keyup", "input:text[datetimeOnly]", function() {$(this).val( $(this).val().replace(/[^0-9:\-]/gi,"") );});

	 
	
	
	/*로그인 페이지*/
	$(".body-sign .center-sign .logo").mouseout(function(){
		$(".logo").css("background-color", "#1483F6");
		$(".logo").css("color", "#fff");
		$(".logo").css("text-decoration", "none");
		
	});
	
	$(".body-sign .center-sign .logo").mouseover(function(){
		$(".logo").css("background-color", "#303030");
		$(".logo").css("color", "#fff");
		$(".logo").css("text-decoration", "none");
		
	});
	
	$("#login_submit").on("click", function(e){
		var $userid=$("#userid");
		var $userpw=$("#userpw");
		if($userid.val()==""){
			tWarning("아이디를 입력해 주세요.");
			$userid.focus();
			return;
		}
		
		if($userpw.val()==""){
			tWarning("비밀번호를 입력해 주세요.");
			$userpw.focus();
			return;
		}
		
		$("#login_post").submit();
	});
	
	
	//상단 메뉴 제어
	$("#sidebar .submenu").on("mouseover", function(e){
		/* $("#userbox").hide("fast"); */	
		$(".gnb_bg").slideDown("fast");
		$("#sidebar > ul li ul").slideDown("fast");			
	});	
	
	$(".gnb_bg").on("mouseout", function(e){
		$(".inner-wrapper").on("mouseover", function(e){			
				$(".gnb_bg").slideUp("fast");
				$("#sidebar > ul li ul").slideUp("fast");	
				$("#userbox").show("fast");
		});
	});
	
	$(".inner-wrapper").click(function(){
		$(".gnb_bg").slideUp("fast");
		$("#sidebar > ul li ul").slideUp("fast");	
		$("#userbox").show("fast");
	});
	
	
	
	//회원 정보 변경
	$("#memberUpdate").click(function(){	
		$("#update_userid").val($("#login_userid").val());
		$("#update_username").val($("#login_username").val());
	});

	
	


 	// 자재정보관리 > 자재등록
	$("#materialNewRegistration").on("click", function(e){
		var no=$("#management_write_no").val();
		var $mcode=$("#input_mcode").val();
		var $materialName=$("#input_materialName").val();
		var $properInventory=$("#input_properInventory").val();

		
		if($.trim($mcode)==""){			
			tWarning("자재코드를 입력해 주세요.");
			$("#input_mcode").focus();
			return;
		}
		
		if($.trim($materialName)==""){			
			tWarning("자재명을 입력해 주세요.");
			$("#input_materialName").focus();
			return;
		}
	
		
		if($.trim($properInventory)==""){			
			tWarning("적정재고량을 입력해 주세요.");
			$("#input_properInventory").focus();
			return;
		}		

		var html="";
		var count=no-1;
		html +="<tr id='list_"+count+"'>";
		html +="<td class='text-center'>"+no+"</td>";					
		html +="<td class='text-right'><input type='text' id='list_"+count+"_mcode' name='list["+count+"].mcode' value='"+$mcode+"' class='material-input port'></td>";
		html +="<td class='text-right'><input type='text' id='list_"+count+"_materialName'    name='list["+count+"].materialName' value='"+$materialName+"' class='material-input'></td>";
		html +="<td class='text-right'><input type='text' id='list_"+count+"_properInventory' name='list["+count+"].properInventory' value='"+$properInventory+"' class='material-input' numberonly='true'></td>";																					

		//html +="<td class='text-center'><button type='button' class='btn-sm btn-danger' onclick='materialRemove("+count+")'>삭제</button></td>";
		
		html +="</tr>";
		
		$(".blank").remove();
		$("#management_write_tbody").append(html);
		
		no++;
		$("#management_write_no").val(no);
		$("#input_mcode").val("");
		$("#input_materialName").val("");
		$("#input_properInventory").val("");
				
	});
	
	
	$("#materialRegistrationSave").on("click", function(){
		if(!confirmData()){
			tError("중복된  코드가 존재 합니다. ");
			return;
		}
		
		var no=$("#management_write_no").val();
		if(no>1){
			var formdata = $("#materialRegistrationSaveForm").serialize();
			$.ajax({
				 url: 'write.do',
				 type: 'POST',
				 data:formdata,
				 dataType: 'text',
				 success: function(msg) {				        
				        if($.trim(msg)=="success"){
				        	tSuccess("등록 되었습니다.");	
				        	var blank='<tr class="blank">';
				        	blank+='<td colspan="4" class="text-center"> 데이터를 입력해 주세요.</td>';
				        	blank+='</tr>';
				        	$("#management_write_tbody").html(blank);
				        	$("#management_write_no").val(1);
				        }else if($.trim(msg)=="duplicate"){
				        	tError("이미  등록한  코드가 존재 합니다. ");
				        }else if($.trim(msg)=="blank"){
				        	tWarning("값을 모두 입력해 주세요.");
				        }else{				        	
				        	tError("등록에 실패 하였습니다.");
				        	console.log(msg);
				        }
				 },
				 error:function(msg){
					   	tError("데이터 등록 오류 입니다.");
					   	console.log(msg);
				 }
			});  			
		}				
	});
 	// end 자재정보관리 > 자재등록	
 
 
});

function materialRemove(count){
	$("#list_"+count).remove();
}


//회원 정보 변경
function cUpdateInfoAjax(){
	var home=$("#home").val();
	var userid=$.trim($("#update_userid").val());
	var username=$.trim($("#update_username").val());
	var userpw=$.trim($("#update_userpw").val());
	var pwck=$.trim($("#update_userpw_check").val());

	if(userid==""){
		tInfo("아이디를 입력하세요.");
		$("#update_userid").focus();
		return;
	}
	
	if(username==""){
		tInfo("이름을 입력하세요.");
		$("#update_username").focus();
		return;
	}
		
	if(userpw==""){		
		tInfo("비밀번호를 입력하세요.");
		$("#update_userpw").focus();
		return;
	}
	
	if(pwck==""){
		tInfo("비밀번호 확인을 입력하세요.");
		$("#update_userpw_check").focus();
		return;
	}
	
	if(userpw!=pwck){
		tWarning('비밀번호와 비밀번호 확인이 일치하지 않습니다.');		
		$("#update_userpw").focus();
		$("#update_userpw").val("");
		$("#update_userpw_check").val("");
		return;
	}
			
	$.ajax({
		url:home+"/member/update.do",
		type:"post",
		data:{
			userid:userid,
			username:username,			
			userpw:userpw			
		},
		dataType:"text",
		success:function(result){			
			if($.trim(result)=='success'){
				tSuccess("정보를 변경 했습니다.");			
				setTimeout(function() {
					location.reload();
				}, 1500);
			}
		},
		error:function(result){
			tError("정보를 변경하시 못 했습니다.");
		}		
	});
	
}

function cInfoClose(){
	$("#update_username").val("");
	$("#update_userpw").val("");
	$("#update_userpw_check").val("");
	$.magnificPopup.close();
	
}


/**
 * 
// 자재정보관리 > 자재등록
 * 
 * @returns
 */
function confirmData(){
	
	/* 	var formdata = $("#materialRegistrationSaveForm").serializeArray();
		

		var result = { };
		$.each(formdata, function(index, item) {
		    result[this.name] = this.value;
		  //  console.log(item.name);
		}); */
		
		
		var arr = $('table').find('#management_write_tbody').find("tr");
		var dcount=0;
		$(".port").removeClass('port-on');	
		for (var i = 1; i < arr.length; i++){
			
			/*
		        i = 1 부터 시작하는 이유
		        아래의 for문 종료 시점이 j < i 이기 때문에 0 미만은 없으니까!!
		    */
		    for (var j = 0; j < i; j++) {
		        /*
		            종료시점이 j < i 인 이유
		            비교값에 자신과 같은 요소를 비교하면 모든값이 중복이 되어버리기 때문에
		            자신의 값 전까지만 비교하도록 조건을 설정했다!!
		        */
		        var arrI = $(arr[i]).find(".port"); //비교하고 싶은 항목이 있는곳 찾아가기
		        var arrJ = $(arr[j]).find(".port"); //비교하고 싶은 항목이 있는곳 찾아가기
		        var arrItxt = arrI.val(); //비교항목 텍스트 추출하기
		        var arrJtxt = arrJ.val(); //비교항목 텍스트 추출하기
		 	     
		        if(arrItxt == arrJtxt) { //비교항목 텍스트 비교	          
		            $('#'+arrI.attr("id")).addClass("port-on");
		            $('#'+arrJ.attr("id")).addClass("port-on");
		            dcount++;
		        } else{	      
		        }
		    }
		}
		if(dcount==0){
			$(".port").removeClass('port-on');	
			return true;
		}else{
			return false;
		}
		
}



/**
 * 
 * 
 * 자재정보관리 목록 조회
 * 
 */
function listManagementMaterialAjaxSearch(page){
	$('#Progress_Loading').show(); //ajax실행시 로딩바를 보여준다.
	var mcode=$("#mcode").val();
	var materialName=$("#materialName").val();	
	$.ajax({		
		url:"listAjaxSearch.do",
		type:"post",
		data:{
			'mcode':mcode,
			'materialName':materialName,
			'page':page
		},
		dataType:"json",		
		success:function(result){	
			
			$("#selectMaterialSearchTotalCount").text(numberWithCommas(result.totalCount));			
			
			//더보기
			if(result.materialList.length >0){			
				//핸들바 템플릿 가져오기
				var source = $("#template").html();
				var template = Handlebars.compile(source);
				//배열 형식 이라 자동으로 매칭							 
				$("#management_search_tbody").append(template(result.materialList));	
			
				if(result.page <(result.tempEndPage)){				
					var more="<button class='btn btn-primary'	onclick='listManagementMaterialAjaxSearch("+(parseInt(result.page)+1)+")'>더보기</button>";							
					$("#management_search_pagining").html(more);								
				}else{
					$("#management_search_pagining").html("");
				}
			}else{
				
				$("#management_search_tbody").html('<tr><td class="text-center" colspan="5">조회 결과가 없습니다.</td></tr>');
				
				$("#management_search_pagining").html("");	
			}
			
			$('#Progress_Loading').hide(); //ajax종료시 로딩바를 숨겨준다.
		},
		error:function(result){
			console.log(result);
			
			$('#Progress_Loading').hide(); //ajax종료시 로딩바를 숨겨준다.
		}		
	});
}

function maSearch(){
	$("#management_search_tbody").html("");	
	$("#management_search_pagining").html("");	
	listManagementMaterialAjaxSearch(1);	
}

function materialDelete(maid){
	
	toastr.remove();
	toastr.clear();
	var content="정말 삭제 하시겠습니까?\n BOM에 등록한 자재도 같이 삭제 처리 됩니다.";
	toastr.info(
		"<div class='text-center'><button type='button' id='confirmationRevertYes' class='btn btn-success btn-custom2'>&nbsp;&nbsp;예&nbsp;&nbsp;</button>" +
		"&nbsp;&nbsp;&nbsp;<button type='button' id='confirmationRevertNo' class='btn btn-danger btn-custom2'>아니오</button><div>" ,
		content,				
	  {
	      closeButton: false,
	      allowHtml: true,
	      onShown: function (toast) {
	          $("#confirmationRevertYes").click(function(){
	        	
	        	  $.ajax({		
	        			url:"materialDelete.do",
	        			type:"post",
	        			data:{
	        				'maid':maid
	        			},
	        			dataType:"text",		
	        			success:function(result){
	        				tSuccess("삭제 처리 되었습니다.");
	        				//$("#mcode").val("");
	    					//$("#materialName").val("");	
	    					maSearch();
	        			},
	        			error:function(result){
	        				console.log(result);
	        			}
	        		});
	          });	         
	          $("#confirmationRevertNo").click(function(){	        	  
	        		toastr.remove();
	        		toastr.clear();
		      });	          
	        }
	  });
}

function materialChangeButton(maid){
	$(".input-"+maid).addClass("material-input3");
	$(".input-"+maid).attr("readonly", false); 	
	$("#up-"+maid).show();
	$("#ch-"+maid).hide();
}

function materialUpdate(mcode, maid){	
	var mcode=$("#mcode-"+maid).val();
	var materialName=$("#materialName-"+maid).val();	
	var	properInventory=$("#properInventory-"+maid).val();

	if(mcode==""){
		tWarning("자재코드를 입력해 주세요.");
		$("#mcode-"+maid).focus();
		return;
	}
	if(materialName==""){
		tWarning("자재명을 입력해 주세요.");
		$("#materialName-"+maid).focus();
		return;
	}
	if(properInventory==""){
		tWarning("적정재고량을 입력해 주세요.");
		$("#properInventory-"+maid).focus();
		return;
	}
	
	$.ajax({		
			url:"materialUpdate.do",
			type:"post",
			data:{
				'mcode':mcode,
				'maid':maid,
				'materialName':materialName,
				'properInventory':properInventory
			},
			dataType:"text",		
			success:function(result){				
				if($.trim(result)=="success"){
					$(".input-"+maid).removeClass("material-input3");
					$(".input-"+maid).attr("readonly", true); 
					$("#up-"+maid).hide();
					$("#ch-"+maid).show();					
					tSuccess("수정 처리 되었습니다.");
					//$("#mcode").val("");
					//$("#materialName").val("");	
					//maSearch();
				}else if($.trim(result)=="duplicate"){
					tError("중복된 코드 입니다.");
				}else {
					tWarning("수정에 실패 하였습니다.");
				}
			},
			error:function(result){
				console.log(result);
			}
	});

}



/**
 * 
 * 
 * 자재정보관리 BOM 등록
 * 
 * 
 * 
 * 
 */

function getByMcodeAutoCompleate(event){
	var id=$(event).attr("id");
	$.ajax({
		url:'getBySearchList.do',
		data:{
			"mcode":$(event).val()
		},
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.materialList.length>0){
				var html="";
				$.each(result.materialList, function(index, item){				
					html +='<div class="tt-suggestion"  onclick="ttAutocompleteTextClick(this)">'+item.mcode+'</div>';				
				});	
				
				$("#tt-dataset_"+id).html(html);
				$("#tt-menu_"+id).show();				
			}else{
				$("#tt-menu_"+id).hide();
			}							
		},
		error:function(result){
			console.log(result);
		}		
	});   
}

//autocomplete action

function autoCompleteHide(e){
	$(e).hide();
}

function ttAutocompleteTextClick(e){
	var mcode=$(e).text();
	var div=$(e).parent("div").parent("div").parent("div").children(".form-tt").val(mcode);
	var id=$(e).parent("div").parent("div").parent("div").children(".form-tt").attr("id");
	
	$.ajax({
		url:"getByMcodeInfo.do",
		data:{'mcode':mcode},
		type:"post",
		dataType:"json",
		success:function(data){
			$("#list_"+id+"_materialName").val(data.materialInfo.materialName);
			$("#list_"+id+"_materialQuantity").val(data.materialInfo.properInventory);
		},
		error:function(result){
			console.log(result);
		}
							
	});
	
}


function addMatterial(){
	var no= $("#addMatterial_no").val();
	
	var html='';
	html+='<div class="form-group">';
	html+='<div class="col-sm-4">';
	html+='	<label class="col-sm-4 control-label">자재 코드:</label>';
	html+='	<div class="col-sm-8">';
	html+='	<input type="text" title="자재 코드" name="list['+no+'].mcode" id="'+no+'" class="form-control form-tt" onkeyup="getByMcodeAutoCompleate(this)"   autocomplete="off">';
	
	
	html+='<div class="tt-menu" id="tt-menu_'+no+'" onmouseleave="autoCompleteHide(this)">';
	html+='<div class="tt-dataset tt-dataset-states" id="tt-dataset_'+no+'" >';										
	html+='</div>';
	html+='</div>';	
	
	
	html+='</div>';
	html+='</div>';
	html+='<div class="col-sm-5">';
	html+='<label class="col-sm-3 control-label">자재 명:</label>';
	html+='<div class="col-sm-9">';
	html+='	<input type="text" title="자재 명" name="list['+no+'].materialName" id="list_'+no+'_materialName" class="form-control">';
	html+='</div>';
	html+='</div>';
	html+='<div class="col-sm-3">';
	html+='	<label class="col-sm-5 control-label">자재 수량:</label>';
	html+='	<div class="col-sm-7">';
	html+='	<input type="text" title="자재 수량" name="list['+no+'].materialQuantity" id="list_'+no+'_materialQuantity" class="form-control" numberonly="true" style = "text-align:right;">';
	html+='</div>';
	html+='</div>';									
	html+='</div>';
	
	$("#bom_material_input").append(html);
	no++;
	$("#addMatterial_no").val(no);
}


function bomWriteSubmit(){
	var pcode=$("#pcode").val();
	var productName=$("#productName").val();
	
	if(pcode==""){
		tWarning("제품 코드를 입력해 주세요.");
		$("#pcode").focus();
		return;
	}

	
	if(productName==""){
		tWarning("제품 명을 입력해 주세요.");
		$("#productName").focus();
		return;
	}

	
	var frm = $("#bomWriteFrm :input").not(":input[type=hidden]").not(":input[type=button]");
	var blankResult=true;
	frm.each(function(idx, ele){		
		if("" == $("#"+ele.id).val()){
			tWarning($("#"+ele.id).attr("title") + "을(를) 입력하세요");
			$("#"+ele.id).focus();
			blankResult =false;
			return false;
		};
	});

	if(!blankResult) return;

	var formdata = $("#bomWriteFrm").serialize();
	$.ajax({
		 url: 'bomWrite.do',
		 type: 'POST',
		 data:formdata,
		 dataType: 'text',
		 success: function(msg) {				        
			   if($.trim(msg)=="success"){
				   $("#addMatterial_no").val("1");
				   $("#pcode").val("");
				   $("#productName").val("");				   
				   var source=$("#successTemplate").html();
				   var template=Handlebars.compile(source);				   
				   $("#bom_material_input").html(template());
		        	tSuccess("등록 되었습니다.");	
		        	
		        }else if($.trim(msg)=="duplicate"){
		        	tError("이미  등록한 BOM 제품  코드 입니다.");
		        }else if($.trim(msg)=="blank"){
		        	tWarning("값을 모두 입력해 주세요.");
		        }else if($.trim(msg)=="notcode"){
		        	tWarning("등록되지 않은 자재 코드가 존재 합니다.\n자재코드를 확인해 주세요.");
		        }else{
		        	tError("등록에 실패 하였습니다.");
		        	console.log(msg);
		        }
		 },
		 error:function(msg){
			   	tError("데이터 등록 오류 입니다.");
			   	console.log(msg);
		 }
	});  			
			
}



/**
 * 
 * 
 * 자재정보관리 BOM 수정
 * 
 * 
 * 
 * 
 */
function bomUpdateSubmit(){
	
	toastr.remove();
	toastr.clear();
	var content="수정 하시겠습니까?";
	toastr.info(
		"<div class='text-center'><button type='button' id='confirmationRevertYes' class='btn btn-success btn-custom2'>&nbsp;&nbsp;예&nbsp;&nbsp;</button>" +
		"&nbsp;&nbsp;&nbsp;<button type='button' id='confirmationRevertNo' class='btn btn-danger btn-custom2'>아니오</button><div>" ,
		content,				
	  {
	      closeButton: false,
	      allowHtml: true,
	      onShown: function (toast) {
	          $("#confirmationRevertYes").click(function(){	        	
	        	  bomUpdateProSess();
	          });	         
	          $("#confirmationRevertNo").click(function(){	        	  
	        		toastr.remove();
	        		toastr.clear();
		      });	          
	        }
	  });

}

function bomUpdateProSess(){
	

	var pcode=$("#pcode").val();
	var productName=$("#productName").val();
	
	if(pcode==""){
		tWarning("제품 코드를 입력해 주세요.");
		$("#pcode").focus();
		return;
	}

	
	if(productName==""){
		tWarning("제품 명을 입력해 주세요.");
		$("#productName").focus();
		return;
	}

	
	var frm = $("#bomUpdateFrm :input").not(":input[type=hidden]").not(":input[type=button]");
	var blankResult=true;
	frm.each(function(idx, ele){		
		if("" == $("#"+ele.id).val()){
			tWarning($("#"+ele.id).attr("title") + "을(를) 입력하세요");
			$("#"+ele.id).focus();
			blankResult =false;
			return false;
		};
	});

	if(!blankResult) return;

	var formdata = $("#bomUpdateFrm").serialize();
	$.ajax({
		 url: 'bomUpdate.do',
		 type: 'POST',
		 data:formdata,
		 dataType: 'text',
		 success: function(msg) {				        
			   if($.trim(msg)=="success"){		
				   location.href="bomList.do?msg=success";        	
		     
		        }else if($.trim(msg)=="duplicate"){
		        	tError("이미  등록한 BOM 제품  코드 입니다.");
		        }else if($.trim(msg)=="blank"){
		        	tWarning("값을 모두 입력해 주세요.");
		        }else if($.trim(msg)=="notcode"){
		        	tWarning("등록되지 않은 자재 코드가 존재 합니다.\n자재코드를 확인해 주세요.");
		        }else{
		        	tError("수정에 실패 하였습니다.");
		        	console.log(msg);
		        }
		 },
		 error:function(msg){
			   	tError("데이터 등록 오류 입니다.");
			   	console.log(msg);
		 }
	});  			
	
	
}

function bomCancel(){
	location.href="bomList.do";
}




/**
 * 
 * BOM 조회 , 삭제
 * 
 */


function updateSelectMessage(){
	var msg=$("#msg").val();
	if(msg=="update"){
		tWarning("수정할 BOM을 선택해 주세요.");
	}else if(msg=="success"){
		tSuccess("수정처리 되었습니다.");
	}
}

function bomListAjaxSearchList(page){
	$('#Progress_Loading').show(); //ajax실행시 로딩바를 보여준다.
	var pcode=$("#pcode").val();
	var productName=$("#productName").val();	
	$.ajax({		
		url:"bomListAjaxSearch.do",
		type:"post",
		data:{
			'pcode':pcode,
			'productName':productName,
			'page':page
		},
		dataType:"json",		
		success:function(result){	
			
			$("#bomSearchTotalCount").text(numberWithCommas(result.totalCount));			
			
			//더보기
			if(result.bomList.length >0){			
				//핸들바 템플릿 가져오기
				var source = $("#template").html();
				var template = Handlebars.compile(source);
				//배열 형식 이라 자동으로 매칭							 
				$("#bom_search_tbody").append(template(result.bomList));	
			
				if(result.page <(result.tempEndPage)){				
					var more="<button class='btn btn-primary'	onclick='bomListAjaxSearchList("+(parseInt(result.page)+1)+")'>더보기</button>";							
					$("#bom_search_pagining").html(more);								
				}else{
					$("#bom_search_pagining").html("");
				}
			}else{				
				$("#bom_search_tbody").html('<tr><td class="text-center" colspan="5">조회 결과가 없습니다.</td></tr>');				
				$("#bom_search_pagining").html("");	
			}
			
			$('#Progress_Loading').hide(); //ajax종료시 로딩바를 숨겨준다.
		},
		error:function(result){
			console.log(result);
			
			$('#Progress_Loading').hide(); //ajax종료시 로딩바를 숨겨준다.
		}		
	});
}

function bomSearch(){
	$("#bom_search_tbody").html("");	
	$("#bom_search_pagining").html("");	
	bomListAjaxSearchList(1);	
}

function bomToggleMaterialList(event){
	var bomid=$(event).attr("data-id");	
	var classValue=$("#tr-"+bomid).attr("class");
	if(classValue==undefined || classValue==""){
		$("#tr-"+bomid).addClass("info");
	}else{
		$("#tr-"+bomid).removeClass("info");
	}
	
	$('#Progress_Loading').show(); //ajax실행시 로딩바를 보여준다.
	$.ajax({		
		url:"bomToggleMaterialList.do",
		type:"post",
		data:{
			'bomid':bomid
		},
		dataType:"json",		
		success:function(result){	
	
			if(result.bomToggleMaterialList.length >0){			
				//핸들바 템플릿 가져오기
				var source = $("#template-bomToggleMaterialList").html();
				var template = Handlebars.compile(source);
				//배열 형식 이라 자동으로 매칭							 
				$("#bom-toggle-data-"+bomid).html(template(result.bomToggleMaterialList));	

			}else{				
				$("#bom-toggle-data-"+bomid).html('<tr><td class="text-center" colspan="4">데이터가 없습니다.</td></tr>');				
			}
			
			$('#Progress_Loading').hide(); //ajax종료시 로딩바를 숨겨준다.
		},
		error:function(result){
			$('#Progress_Loading').hide(); //ajax종료시 로딩바를 숨겨준다.
			console.log(result);
		}
	});
	
}


function bomDelete(bomid){
	
	toastr.remove();
	toastr.clear();
	var content="정말 삭제 하시겠습니까?";
	toastr.info(
		"<div class='text-center'><button type='button' id='confirmationRevertYes' class='btn btn-success btn-custom2'>&nbsp;&nbsp;예&nbsp;&nbsp;</button>" +
		"&nbsp;&nbsp;&nbsp;<button type='button' id='confirmationRevertNo' class='btn btn-danger btn-custom2'>아니오</button><div>" ,
		content,				
	  {
	      closeButton: false,
	      allowHtml: true,
	      onShown: function (toast) {
	          $("#confirmationRevertYes").click(function(){
	        	
	        	  $.ajax({		
	        			url:"bomDelete.do",
	        			type:"post",
	        			data:{
	        				'bomid':bomid
	        			},
	        			dataType:"text",		
	        			success:function(result){
	        				tSuccess("삭제 처리 되었습니다.");
	        				bomSearch();
	        			},
	        			error:function(result){
	        				console.log(result);
	        			}
	        		});
	          });	         
	          $("#confirmationRevertNo").click(function(){	        	  
	        		toastr.remove();
	        		toastr.clear();
		      });	          
	        }
	  });
}


function bomUpdateMove(bomid){	
	$("#update_bomid").val(bomid);
	$("#bormUpdatFrm").submit();
}



$('.accordian-body').on('show.bs.collapse', function () {
    $(this).closest("table")
        .find(".collapse.in")
        .not(this)
        .collapse('toggle')
})





/**
 * 
 * 
 *  생산  - 계획 등록
 * 
 * 
 * 
 */

function planProductAdd(){		
	
	  var html="";
      html+='<tr>';
      html+='<td>'+(planNo+1)+'</td>';
      html+='<td id="list_0_div">';
      html+='<input type="text" title="제품코드" name="list['+planNo+'].pcode" id="'+planNo+'" onkeydown="getByPlanProductAutoCompleate(this)"  ';			
      
      html+='autocomplete="off" list="tt-menu_'+planNo+'" class="form-control" onfocus="getByPlanProductAutoCompleate(this)"  onblur="planProductAutocompleteTextClick(this)"  onchange="planProductAutocompleteTextClick(this)"     > ';
      
      html+='<datalist id="tt-menu_'+planNo+'" >';
      html+='</datalist>';
      
      html+='</td> ';
      html+='<td> ';
      html+='<input type="text" title="제품명"  autocomplete="off" name="list['+planNo+'].planProductName" id="list_'+planNo+'_planProductName"  class="form-control"> ';
      
      html+='</td> ';
      html+='<td> ';
      html+='<input type="text" title="개수" name="list['+planNo+'].planProductQuantity" id="list_'+planNo+'_planProductQuantity"  class="form-control"  numberonly="true"> ';
      html+='</td> ';
      html+='<td> ';
      html+='<input type="text" title="날짜" name="list['+planNo+'].deadline" id="list_'+planNo+'_deadline"  class="form-control" onclick="palnDataPicker(this)"  ';
      html+='onmouseover="palnDataPicker(this)" readonly>	 ';		  
      html+='</td>		 ';	
      html+='<td> ';
      html+='<input type="text" title="비고" name="list['+planNo+'].remarks" id="list_'+planNo+'_remarks"  class="form-control not"> ';
      html+='</td>		 ';																			
      html+='</tr>	 ';
	
			
	$("#plan_wirte_tbody").prepend(html);	
	planNo++;	 
}


function planProductWrite(){
	
	var frm = $("#planProductWriteFRM :input").not(":input[type=hidden]").not(":input[type=button]").not(":input[title='비고']");
	var blankResult=true;
	frm.each(function(idx, ele){		
		if("" == $("#"+ele.id).val()){
			tWarning($("#"+ele.id).attr("title") + "을(를) 입력하세요");
			$("#"+ele.id).focus();
			blankResult =false;
			return false;
		};
	});

	if(!blankResult) return;

	
	var formdata = $("#planProductWriteFRM").serialize();
	$.ajax({
		 url: 'write.do',
		 type: 'POST',
		 data:formdata,
		 dataType: 'text',
		 success: function(msg) {				        
			   if($.trim(msg)=="success"){
					location.href="list.do?msg=success";
		        }else if($.trim(msg)=="blank"){
		        	tWarning("값을 모두 입력해 주세요.");
		        }else if($.trim(msg)=="notcode"){
		        	tWarning("BOM에 등록되지 제품 코드가 존재 합니다.\n 제품 코드를 확인해 주세요.");
		        }else if($.trim(msg)=="error"){
		        	tError("등록에 실패 하였습니다.");		        	
		        }else {
		        	console.log(msg);
		        }
		 },
		 error:function(msg){
			   	tError("데이터 등록 오류 입니다.");
			   	console.log(msg);
		 }
	});  			
	

}


function palnDataPicker(e){
	$(e).datepicker({
		format: "yyyy-mm-dd",	//데이터 포맷 형식(yyyy : 년 mm : 월 dd : 일 )	
	    language : "kr" ,	//달력의 언어 선택, 그에 맞는 js로 교체해줘야한다. 
    	startDate: '+1d'
	});
}


function getByPlanProductAutoCompleate(event){
	var id=$(event).attr("id");
	$.ajax({
		url:'bomSearchList.do',
		data:{
			"pcode":$(event).val()
		},
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.bomList.length>0){
				var html="";
				$.each(result.bomList, function(index, item){													
					html +='<option value="'+item.pcode+'" onclick="planProductAutocompleteTextClick(this)"    onfocus="planProductAutocompleteTextClick(this)" data-id="'+id+'">';				
				});	
				$("#tt-menu_"+id).html(html);
			}else{
				$("#tt-menu_"+id).html("");
			}							
		},
		error:function(result){
			console.log(result);
		}		
	});   
}



function planProductAutocompleteTextClick(e){
	var pcode=$(e).val();
	var id=$(e).attr("id");
	$("#"+id).val(pcode);
	
	$.ajax({
		url:"getByPcodeInfo.do",
		data:{'pcode':pcode},
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.bomInfo==null){
				$("#list_"+id+"_planProductName").val("");
				$("#list_"+id+"_planProductQuantity").val("");
			}else{
				$("#list_"+id+"_planProductName").val(data.bomInfo.productName);
				$("#list_"+id+"_planProductQuantity").val(data.bomInfo.productQuantity);
			}

		},
		error:function(result){
			console.log(result);
		}
							
	}); 
	
}





/**
 * 
 * 
 *  생산  - 조회, 수정, 삭제
 * 
 * 
 * 
 */
function planListAjaxSearchList(page){
	$('#Progress_Loading').show(); //ajax실행시 로딩바를 보여준다.
	var pcode=$("#pcode").val();
	var planProductName=$("#planProductName").val();	
	var startDate=$("#startDate").val();
	var endDate=$("#endDate").val();
	$.ajax({		
		url:"planListAjaxSearch.do",
		type:"post",
		data:{
			'pcode':pcode,
			'planProductName':planProductName,
			'page':page,
			'startDate':startDate,
			'endDate':endDate
		},
		dataType:"json",		
		success:function(result){	
			
			$("#planSearchTotalCount").text(numberWithCommas(result.totalCount));			
			
			//더보기
			if(result.planList.length >0){			
				//핸들바 템플릿 가져오기
				var source = $("#planTemplate").html();
				var template = Handlebars.compile(source);
				//배열 형식 이라 자동으로 매칭							 
				$("#plan_search_tbody").append(template(result.planList));	
			
				if(result.page <(result.tempEndPage)){				
					var more="<button class='btn btn-primary'	onclick='planListAjaxSearchList("+(parseInt(result.page)+1)+")'>더보기</button>";							
					$("#plan_search_pagining").html(more);								
				}else{
					$("#plan_search_pagining").html("");
				}
			}else{				
				$("#plan_search_tbody").html('<tr><td class="text-center" colspan="7">조회 결과가 없습니다.</td></tr>');				
				$("#plan_search_pagining").html("");	
			}
			
			$('#Progress_Loading').hide(); //ajax종료시 로딩바를 숨겨준다.
		},
		error:function(result){
			console.log(result);
			
			$('#Progress_Loading').hide(); //ajax종료시 로딩바를 숨겨준다.
		}		
	});
}

function planSearch(){
	$("#plan_search_tbody").html("");	
	$("#plan_search_pagining").html("");	
	planListAjaxSearchList(1);	
}

//검색 초기화
function planSearchReset(){
	$("#pcode").val("");
	$("#planProductName").val("");	
	$("#startDate").val("");
	$("#endDate").val("");
	planSearch();
}


function planToggleMaterialList(planId, bomid,planProductQuantity){
	if($("#tr-"+planId).hasClass("info")==true){	
		console.log("info 존재");
		$("#tr-"+planId).removeClass("info");
	}else{
		console.log("info 없음  - info 추가");
		$("#tr-"+planId).addClass("info");
	}
	
	
	$('#Progress_Loading').show(); //ajax실행시 로딩바를 보여준다.
	$.ajax({		
		url:"bomToggleMaterialList.do",
		type:"post",
		data:{
			'bomid':bomid,
			'planId':planId,
			'planProductQuantity':planProductQuantity			
		},
		dataType:"json",		
		success:function(result){	
	
			if(result.bomToggleMaterialList.length >0){			
				//핸들바 템플릿 가져오기
				var source = $("#template-planToggleMaterialList").html();
				var template = Handlebars.compile(source);
				//배열 형식 이라 자동으로 매칭							 
				$("#plan-toggle-data-"+planId).html(template(result.bomToggleMaterialList));	

			}else{				
				$("#plan-toggle-data-"+planId).html('<tr><td class="text-center" colspan="5">데이터가 없습니다.</td></tr>');				
			}
			
			$('#Progress_Loading').hide(); //ajax종료시 로딩바를 숨겨준다.
		},
		error:function(result){
			$('#Progress_Loading').hide(); //ajax종료시 로딩바를 숨겨준다.
			console.log(result);
		}
	});
	
}

function planRegister(){	
	location.href="writeForm.do";
}





function planChangeUpdate(planId){
	$("#planChangeUpdate-"+planId).hide();
	$(".class-"+planId).attr("data-toggle","");
	$(".class-"+planId).attr('onclick', '').unbind('click')
	$(".span-"+planId).hide();

	
	$("#planProductName-"+planId).show();
	$("#planProductQuantity-"+planId).show();
	$("#deadline-"+planId).show();
	$("#remarks-"+planId).show();
	$("#planUpdate-"+planId).show();
}

//수정
function planUpdate(planId,bomid){
	var planProductName=$("#planProductName-"+planId).val();
	var planProductQuantity=$("#planProductQuantity-"+planId).val();
	var deadline=$("#deadline-"+planId).val();
	var remarks=$("#remarks-"+planId).val();
	
	
	
	if($.trim(planProductName)==""){
		tWarning("제품명을 입력하세요.");
		$("#planProductName-"+planId).focus();
		return;
	}
	
	if($.trim(planProductQuantity)==""){
		tWarning("생산수를 입력하세요.");
		$("#planProductQuantity-"+planId).focus();
		return;
	}
	
	if($.trim(deadline)==""){
		tWarning("기한을 입력하세요.");
		$("#deadline-"+planId).focus();
		return;
	}
	

	toastr.remove();
	toastr.clear();
	var content="저장 되어진 불출 신청된 자재들의 수량은 업데이트 되지 않습니다.\n 수정 하시겠습니까?";
	toastr.info(
		"<div class='text-center'><button type='button' id='confirmationRevertYes' class='btn btn-success btn-custom2'>&nbsp;&nbsp;예&nbsp;&nbsp;</button>" +
		"&nbsp;&nbsp;&nbsp;<button type='button' id='confirmationRevertNo' class='btn btn-danger btn-custom2'>아니오</button><div>" ,
		content,				
	  {
	      closeButton: false,
	      allowHtml: true,
	      onShown: function (toast) {
	          $("#confirmationRevertYes").click(function(){

				 $.ajax({
						url:"planUpdate.do",
						type:"post",
						data:{
							'planId':planId,
							'bomid':bomid,
							'planProductName':planProductName,
							'planProductQuantity':planProductQuantity,
							'deadline':deadline,
							'remarks':remarks			
						},
						dataType:"text",
						success:function(result){
							if($.trim(result)=="success"){
								   $("#plan_search_tbody").html("");
								   planListAjaxSearchList(1);	
								   tSuccess("수정  되었습니다.");			
						    }else {
						    	tWarning("오류 입니다.");
						    	console.log(result);
						    }						        
							  
						},
						error:function(result){
							tError("오류 입니다.");
							console.log(result);
						}				
					});
	          });	         
	          $("#confirmationRevertNo").click(function(){	        	  
	        		toastr.remove();
	        		toastr.clear();
		      });	          
	        }
	  });
	
	

	
}



function planDelete(planId){
	toastr.remove();
	toastr.clear();
	var content="저장 되지않은 불출신청된 자재들도 삭제 처리 됩니다.\n정말 삭제 하시겠습니까?";
	toastr.info(
		"<div class='text-center'><button type='button' id='confirmationRevertYes' class='btn btn-success btn-custom2'>&nbsp;&nbsp;예&nbsp;&nbsp;</button>" +
		"&nbsp;&nbsp;&nbsp;<button type='button' id='confirmationRevertNo' class='btn btn-danger btn-custom2'>아니오</button><div>" ,
		content,				
	  {
	      closeButton: false,
	      allowHtml: true,
	      onShown: function (toast) {
	          $("#confirmationRevertYes").click(function(){
	        	
	        	  $.ajax({		
	        			url:"planDelete.do",
	        			type:"post",
	        			data:{
	        				'planId':planId
	        			},
	        			dataType:"text",		
	        			success:function(result){
	        				location.reload();
	        			},
	        			error:function(result){
	        				console.log(result);
	        			}
	        		}); 
	          });	         
	          $("#confirmationRevertNo").click(function(){	        	  
	        		toastr.remove();
	        		toastr.clear();
		      });	          
	        }
	  });
}








/**
 * 
 * 
 *  자재  - 입고신청
 * 
 * 
 * 
 */
function wMaterialApplyPlus(){
	var $mcode=$("#mcode").val();
	var $warehosingMaterialName=$("#warehosingMaterialName").val();
	var $applicationQuantity=$("#applicationQuantity").val();
	
	if($mcode==""){
		tWarning("자재 코드를 입력해 주세요.");
		$("#mcode").focus();
		return;
	}

	if($warehosingMaterialName==""){
		tWarning("자재 명을 입력해 주세요.");
		$("#warehosingMaterialName").focus();
		return;
	}
	
	if($applicationQuantity==""){
		tWarning("신청 수량을 입력해 주세요.");
		$("#applicationQuantity").focus();
		return;
	}

	if(parseInt($applicationQuantity)<1){
		tWarning("1이상의 신청 수량을 입력하세요.");
		$("#applicationQuantity").focus();
		return;
	}
	
	
	var html="";

	html +="<tr id='list_"+no+"'>";
	html +="<td class='text-center'>"+(no+1)+"</td>";					
	html +="<td class='text-right'><input type='text' id='list_"+no+"_mcode' name='list["+no+"].mcode' value='"+$mcode+"' class='material-input2 ' ";
	html +=" readonly >";
	html +="</td>";
	html +="<td class='text-right'><input type='text' id='list_"+no+"_warehosingMaterialName'  name='list["+no+"].warehosingMaterialName' value='"+$warehosingMaterialName+"' class='material-input2 ' readonly ></td>";
	html +="<td class='text-right'><input type='text' id='list_"+no+"_applicationQuantity' name='list["+no+"].applicationQuantity' value='"+$applicationQuantity+"' class='material-input2 ' numberonly='true'  readonly ";
	html +=" data-toggle='tooltip' data-placement='top'  ></td>";																						
	html +="<td class='text-right'><input type='text' id='list_"+no+"_remarks' name='list["+no+"].remarks'  class='form-control' ></td>";
	html +="</tr>";
	
	$(".blank").remove();
	$("#tbody-wMaterialApplyFRM").prepend(html);
	$("#mcode").val("");
	$("#warehosingMaterialName").val("");
	$("#applicationQuantity").val("");
	$("#applicationQuantity").attr("data-original-title" , "");
	$("#applicationQuantity").tooltip("hide");
	no++;
}


function getByWMAmcodeAutoCompleate(event){	
	var listId=$(event).attr("list");
	$.ajax({
		url:'getBySearchList.do',
		data:{
			"mcode":$(event).val()
		},
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.materialList.length>0){
				var html="";
				$.each(result.materialList, function(index, item){				
					html +='<option value="'+item.mcode+'">';				
				});	
				
				$("#"+listId).html(html);
			}else{
				$("#"+listId).html("");
			}							
		},
		error:function(result){
			console.log(result);
		}		
	});   
}

function ttWMAmcodeAutocompleteTextClick(event){
	var mcode=$(event).val();
	var dataNameId=$(event).attr("data-name-id");
	var dataQuantityId=$(event).attr("data-quantity-id");
	var applicationCode=$(event).attr("data-applicationCode");
	$.ajax({
		url:"getByMcodeInfo.do",
		data:{'mcode':mcode},
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.materialInfo !=null){

				$("#"+dataNameId).val(data.materialInfo.materialName);
				$("#properInventory-"+applicationCode).text(data.materialInfo.properInventory);
				$("#currentInventory-"+applicationCode).text(data.materialInfo.currentInventory);
				//$("#"+dataQuantityId).val(data.materialInfo.properInventory);
				var max=(parseInt(data.materialInfo.properInventory)-parseInt(data.materialInfo.currentInventory));
				//console.log(parseInt(data.materialInfo.properInventory) + " :  "+ parseInt(data.materialInfo.currentInventory));
				//$("#"+dataQuantityId).attr("data-original-title" , "적정 재고수량: " +data.materialInfo.properInventory + ",  현재 재고수량: "+data.materialInfo.currentInventory +" , 입고 신청 가능한 최대수량: "+max);
				$("#"+dataQuantityId).attr("data-original-title" ,"입고 신청가능한 최대수량: "+max);
				$("#"+dataQuantityId).attr("max", max);
				$("#"+dataQuantityId).tooltip({
					placement: 'right'
				});		
			}else{
				$("#"+dataNameId).val("");
				$("#"+dataQuantityId).val("");
				$("#"+dataQuantityId).attr("data-original-title" , "");
				$("#"+dataQuantityId).tooltip("hide");
			}
			
		},
		error:function(result){
			console.log(result);
		}
							
	});
	
}

function warehosingMaterialApplySave(){	
	if($("#tbody-wMaterialApplyFRM  input[type=text]").length >0){
		var formdata = $("#warehosingMaterialApplyFRM").serialize();
		$.ajax({
			 url: 'warehosingMaterialsApplyWrite.do',
			 type: 'POST',
			 data:formdata,
			 dataType: 'text',
			 success: function(msg) {				        
				   if($.trim(msg)=="success"){
					   
					   $("#tbody-wMaterialApplyFRM").html('<tr class="blank"><td colspan="5" class="text-center">데이터를 입력해 주세요.</td></tr>');
					   $("#wma_search_tbody").html("");
					   wmaListAjaxSearchList(1);
					   no=1;
					   tSuccess("입고 신청 되었습니다.");
			        }else if($.trim(msg)=="blank"){
			        	tWarning("값을 모두 입력해 주세요.");
			        }else if($.trim(msg)=="notcode"){
			        	tWarning("등록되지 않은 자재 코드가 존재 합니다.\n 자재 코드를 확인해 주세요.");
			        }else if($.trim(msg)=="error"){
			        	tError("저장에 실패 하였습니다.");		        	
			        }else {
			        	console.log(msg);
			        }
			 },
			 error:function(msg){
				   	tError("데이터 등록 오류 입니다.");
				   	console.log(msg);
			 }
		});  			
		

	}else{
		tWarning("데이터를 입력해 주세요. ");
	}
	
}

//중복 등록 코드 확인
function confirmWmaData(){
	
		var arr = $('table').find('#wma_search_tbody').find("tr");
		var dcount=0;
		$(".port").removeClass('port-on');	
		for (var i = 1; i < arr.length; i++){
			
			/*
		        i = 1 부터 시작하는 이유
		        아래의 for문 종료 시점이 j < i 이기 때문에 0 미만은 없으니까!!
		    */
		    for (var j = 0; j < i; j++) {
		        /*
		            종료시점이 j < i 인 이유
		            비교값에 자신과 같은 요소를 비교하면 모든값이 중복이 되어버리기 때문에
		            자신의 값 전까지만 비교하도록 조건을 설정했다!!
		        */
		        var arrI = $(arr[i]).find(".port"); //비교하고 싶은 항목이 있는곳 찾아가기
		        var arrJ = $(arr[j]).find(".port"); //비교하고 싶은 항목이 있는곳 찾아가기
		        var arrItxt = arrI.text(); //비교항목 텍스트 추출하기
		        var arrJtxt = arrJ.text(); //비교항목 텍스트 추출하기
		 	     
		        if(arrItxt == arrJtxt) { //비교항목 텍스트 비교	          
		            $('#'+arrI.attr("id")).addClass("port-on");
		            $('#'+arrJ.attr("id")).addClass("port-on");
		            dcount++;
		        } else{	      
		        }
		    }
		}
		if(dcount==0){
			$(".port").removeClass('port-on');	
			return true;
		}else{
			return false;
		}		
}

function wmaListAjaxSearchList(page){
	$('#Progress_Loading').show(); //ajax실행시 로딩바를 보여준다.
	
	$.ajax({		
		url:"listWarehosingMaterialsApply.do",
		type:"post",
		data:{
			page:page
		},
		dataType:"json",		
		success:function(result){	
			$("#wmaSearchTotalCount").text(numberWithCommas(result.totalCount));			
			
			//더보기
			if(result.listWarehosingMaterialsApply.length >0){	
		
				var source = $("#wmaTemplate").html();
				var template = Handlebars.compile(source);						 
				$("#wma_search_tbody").append(template(result.listWarehosingMaterialsApply));	
			
/*				if(result.page <(result.tempEndPage)){				
					var more="<button class='btn btn-primary'	onclick='wmaListAjaxSearchList("+(parseInt(result.page)+1)+")'>더보기</button>";							
					$("#wma_search_pagining").html(more);								
				}else{
					$("#wma_search_pagining").html("");
				}
				*/
				
				$(".process-status-0").addClass("dark");
				$(".process-status-1").addClass("success");
				$(".process-status-2").addClass("warning");
				$(".process-status-3").addClass("danger");
				
				confirmWmaData();
				
				
			 	 $('#wmaDataTable').DataTable( {
				        "paging":   false, 			        
				        "info":     false,
				        "searching":     true,
				        
				        "scrollCollapse": false,
				        "pageLength": 300,
				        "order": [ [ 0, "desc" ] ],
				        "lengthChange": false,				      							        
				  });

				 $("input[type='search']").attr("placeholder", "검색");
				
				
			}else{				
				$("#wma_search_tbody").html('<tr><td class="text-center" colspan="8">입고 신청한 데이터가 없습니다.</td></tr>');				
				$("#wma_search_pagining").html("");	
			}
			
			$('#Progress_Loading').hide(); //ajax종료시 로딩바를 숨겨준다.
		},
		error:function(result){
			console.log(result);
			
			$('#Progress_Loading').hide(); //ajax종료시 로딩바를 숨겨준다.
		}		
	});
	
	
}


function wmaChangeUpdate(applicationCode){
	$("#wmaChangeUpdate-"+applicationCode).hide();
	$(".span-"+applicationCode).hide();
		
	$("#mcode-"+applicationCode).show();
	$("#warehosingMaterialName-"+applicationCode).show();
	
	$("#applicationQuantity-"+applicationCode).val($("#applicationQuantity-"+applicationCode).val().replace(/[^0-9]/gi,"") );
	
	$("#applicationQuantity-"+applicationCode).show();
	$("#remarks-"+applicationCode).show();
	$("#wmaUpdate-"+applicationCode).show();	
	$(".td-"+applicationCode).attr('onclick', '').unbind('click')	
}

function moveWmaProcess(applicationCode, processStatus){
	if(processStatus=="1"){
		tWarning("입고처리 완료된 자재입니다.");
		return;
	}
	location.href="wmaProcess.do?applicationCode="+applicationCode;
}

//수정
function wmaUpdate(applicationCode){
	var mcode=$("#mcode-"+applicationCode).val();
	var warehosingMaterialName=$("#warehosingMaterialName-"+applicationCode).val();
	var applicationQuantity=$("#applicationQuantity-"+applicationCode).val();
	var remarks=$("#remarks-"+applicationCode).val();
	
	if($.trim(mcode)==""){
		tWarning("자재 코드를 입력하세요.");
		$("#mcdoe-"+applicationCode).focus();
		return;
	}
	
	if($.trim(warehosingMaterialName)==""){
		tWarning("자재명을 입력하세요.");
		$("#warehosingMaterialName-"+applicationCode).focus();
		return;
	}
	
	if($.trim(applicationQuantity)==""){
		tWarning("신청 수량을 입력하세요.");
		$("#applicationQuantity-"+applicationCode).focus();
		return;
	}
	
	if(parseInt($.trim(applicationQuantity))<1){
		tWarning("1이상의 신청 수량을 입력하세요.");
		$("#applicationQuantity-"+applicationCode).focus();
		return;
	}
	
	
	$.ajax({
		url:"wmaUpdate.do",
		type:"post",
		data:{
			'applicationCode':applicationCode,
			'mcode':mcode,
			'warehosingMaterialName':warehosingMaterialName,
			'applicationQuantity':applicationQuantity,
			'remarks':remarks			
		},
		dataType:"text",
		success:function(msg){
			  if($.trim(msg)=="success"){
				   $("#wma_search_tbody").html("");
				   wmaListAjaxSearchList(1);
				   tSuccess("수정  되었습니다.");			
		        }else if($.trim(msg)=="blank"){
		        	tWarning("값을 모두 입력해 주세요.");
		        }else if($.trim(msg)=="notcode"){
		        	tWarning("등록되지 않은 자재 코드 입니다..\n 자재 코드를 확인해 주세요.");
		        }else if($.trim(msg)=="error"){
		        	tError("수정에 실패 하였습니다.");		        	
		        }else {
		        	console.log(msg);
		        }
			  
		},
		error:function(result){
			console.log(result);
		}				
	});
	
	
}

function wmaDelete(applicationCode){

	toastr.remove();
	toastr.clear();
	var content="정말 삭제 하시겠습니까?";
	toastr.info(
		"<div class='text-center'><button type='button' id='confirmationRevertYes' class='btn btn-success btn-custom2'>&nbsp;&nbsp;예&nbsp;&nbsp;</button>" +
		"&nbsp;&nbsp;&nbsp;<button type='button' id='confirmationRevertNo' class='btn btn-danger btn-custom2'>아니오</button><div>" ,
		content,				
	  {
	      closeButton: false,
	      allowHtml: true,
	      onShown: function (toast) {
	          $("#confirmationRevertYes").click(function(){
	        	
	        	  $.ajax({
	        			url:"wmaDelete.do",
	        			type:"post",
	        			data:{
	        				'applicationCode':applicationCode,
	        			},
	        			dataType:"text",
	        			success:function(result){
	        				if($.trim(result)=="success"){
	        					$("#wma_search_tbody").html("");
	        					wmaListAjaxSearchList(1);
	        					tSuccess("삭제 되었습니다.");
	        				}
	        			},
	        			error:function(result){
	        				console.log(result);
	        			}				
	        		});
	          });	         
	          $("#confirmationRevertNo").click(function(){	        	  
	        		toastr.remove();
	        		toastr.clear();
		      });	          
	        }
	  });
	
	
}


function wmaAllDelete(applicationCode){

	toastr.remove();
	toastr.clear();
	var content="정말 전체 삭제 하시겠습니까?";
	toastr.info(
		"<div class='text-center'><button type='button' id='confirmationRevertYes' class='btn btn-success btn-custom2'>&nbsp;&nbsp;예&nbsp;&nbsp;</button>" +
		"&nbsp;&nbsp;&nbsp;<button type='button' id='confirmationRevertNo' class='btn btn-danger btn-custom2'>아니오</button><div>" ,
		content,				
	  {
	      closeButton: false,
	      allowHtml: true,
	      onShown: function (toast) {
	          $("#confirmationRevertYes").click(function(){
	        	
	        	  $.ajax({
	        			url:"wmaAllDelete.do",
	        			type:"post",
	        			dataType:"text",
	        			success:function(result){
	        				if($.trim(result)=="success"){
	        					$("#wma_search_tbody").html("");
	        					wmaListAjaxSearchList(1);
	        					tSuccess("삭제 되었습니다.");
	        				}
	        			},
	        			error:function(result){
	        				console.log(result);
	        			}				
	        		});
	          });	         
	          $("#confirmationRevertNo").click(function(){	        	  
	        		toastr.remove();
	        		toastr.clear();
		      });	          
	        }
	  });
	
	
}

function maxCheck(object){
   if (parseInt(object.value) > parseInt(object.max)){
   	 object.value = parseInt(object.max);
   }    
}

function wmaSuccessDelete(){

	toastr.remove();
	toastr.clear();
	var content="정말 입고처리 완료건만 삭제 하시겠습니까?";
	toastr.info(
		"<div class='text-center'><button type='button' id='confirmationRevertYes' class='btn btn-success btn-custom2'>&nbsp;&nbsp;예&nbsp;&nbsp;</button>" +
		"&nbsp;&nbsp;&nbsp;<button type='button' id='confirmationRevertNo' class='btn btn-danger btn-custom2'>아니오</button><div>" ,
		content,				
	  {
	      closeButton: false,
	      allowHtml: true,
	      onShown: function (toast) {
	          $("#confirmationRevertYes").click(function(){
	        	
	        	  $.ajax({
	        			url:"wmaSuccessDelete.do",
	        			type:"post",
	        			dataType:"text",
	        			success:function(result){
	        				if($.trim(result)=="success"){
	        					$("#wma_search_tbody").html("");
	        					wmaListAjaxSearchList(1);
	        					tSuccess("삭제 되었습니다.");
	        				}
	        			},
	        			error:function(result){
	        				console.log(result);
	        			}				
	        		});
	          });	         
	          $("#confirmationRevertNo").click(function(){	        	  
	        		toastr.remove();
	        		toastr.clear();
		      });	          
	        }
	  });
	
}





/**
 * 
 * 
 *  자재  - 입고처리
 * 
 * 
 * 
 */
function ttWmaAutoInfo(event){
	var applicationCode=$(event).val();
	$.ajax({
		url:"getByWMAcodeInfo.do",
		data:{'applicationCode':applicationCode},
		type:"post",
		dataType:"json",
		success:function(data){	
			if(data.getByWMAcodeInfo!=null){
				$("#applicationCode").val(applicationCode);
				$("#warehosingMaterialName").val(data.getByWMAcodeInfo.warehosingMaterialName);
				$("#applicationQuantity").val(data.getByWMAcodeInfo.applicationQuantity);
				$("#properInventory").val(data.getByWMAcodeInfo.properInventory);
				$("#currentInventory").val(data.getByWMAcodeInfo.currentInventory);
				
				$("#maid").val(data.getByWMAcodeInfo.maid);
				
				$("#remarks").val(data.getByWMAcodeInfo.remarks);				
			}else{
				$("#applicationCode").val("");
				$("#warehosingMaterialName").val("");
				$("#applicationQuantity").val("");
				$("#properInventory").val("");
				$("#currentInventory").val("");
				$("#maid").val("");
				$("#remarks").val("");	
			}
			
		},
		error:function(result){
			console.log(result);
		}							
	});
		
}

function wmaProcessAdd(){
		
	var processStatus=$("#processStatus").val();	
	var target=document.getElementById("mcode");
	var mcode=target.options[target.selectedIndex].text;

	if($("select option").length==1){		
		tWarning("입고 신청된 자재가 없습니다. 입고신청을  해주세요.");
		return;	
	}
	
	if(processStatus=="1"){
		tWarning("입고처리 완료한 자재 입니다.");
		return;		
	}
	
	if(mcode==""){
		tWarning("자재 코드를 선택해 주세요.");
		return;
	}
	
	var processStatus=$("#processStatus").val();
	var applicationCode=$("#applicationCode").val();
	var warehosingMaterialName=$("#warehosingMaterialName").val();
	var applicationQuantity=$("#applicationQuantity").val();
	var properInventory=$("#properInventory").val();
	var currentInventory=$("#currentInventory").val();
	var remarks=$("#remarks").val();
	var maid=$("#maid").val();
	//신청 재고량 + 현재재고량 >적정 재고량 
	
	var tot=parseInt(applicationQuantity)+parseInt(currentInventory);
	if(tot >parseInt(properInventory) ){
		tWarning("적정 재고수량이 초과되는 입고 신청수량 입니다.\n적정 재고수량을 올리던지 입고 신청수량을 줄여 주세요. ");
		return;
	}
	
	
	var html='';	
	html+='<tr class="text-center">';
	html+='<td>'+(no+1)+'</td>';
	html+='<td>';
	html+='<input type="text" id="list_0_mcode" name="list['+no+'].mcode" value="'+mcode+'" class="material-input-center" readonly>';
	html+='</td>';
	html+='<td>';
	html+='<input type="text" id="list_'+no+'_warehosingMaterialName" name="list['+no+'].warehosingMaterialName" value="'+warehosingMaterialName+'" class="material-input-center" readonly>';
	html+='</td>';
	html+='<td>';
	html+='<input type="text" id="list_'+no+'_applicationQuantity" name="list['+no+'].applicationQuantity" value="'+applicationQuantity+'" class="material-input-center" readonly>';						
	html+='</td>';
	html+='<td>';
	html+='<input type="text" id="list_'+no+'_remarks" name="list['+no+'].remarks" value="'+remarks+'" class="material-input-center" readonly>';												
	html+='<input type="hidden" id="list_'+no+'_applicationCode" name="list['+no+'].applicationCode" value="'+applicationCode+'" class="material-input-center" >';
	html+='<input type="hidden" id="list_'+no+'_properInventory" name="list['+no+'].properInventory" value="'+properInventory+'" class="material-input-center" >';
	html+='<input type="hidden" id="list_'+no+'currentInventory" name="list['+no+'].currentInventory" value="'+currentInventory+'" class="material-input-center" >';
	html+='<input type="hidden" id="list_'+no+'_maid" name="list['+no+'].maid" value="'+maid+'" class="material-input-center" >';
	html+='</td>';
	html+='</tr>';	
	if(no==0){
		$("#tbody-wmaProcess").html("");
	}
	$("#tbody-wmaProcess").prepend(html);
	$("#"+applicationCode).remove();
	no++;
	
	
	$("#processStatus").val("");
	$("#applicationCode").val("");
	$("#warehosingMaterialName").val("");
	$("#applicationQuantity").val("");
	$("#properInventory").val("");
	$("#currentInventory").val("");
	$("#remarks").val("");
	$("#maid").val("");
	
}

function wmaProcessSave(){
	if($("#tbody-wmaProcess  input[type=text]").length >0){
		var formdata = $("#wrmProcesssfrm").serialize();
		$.ajax({
			 url: 'wmaProcessSave.do',
			 type: 'POST',
			 data:formdata,
			 dataType: 'text',
			 success: function(msg) {				        
				   if($.trim(msg)=="success"){					   
					   $("#tbody-wmaProcess").html('<tr class="blank"><td colspan="5" class="text-center">데이터를 입력해 주세요.</td></tr>');
					   no=0;
					   tSuccess("입고 처리 되었습니다.");
					  
					   
			        }else if($.trim(msg)=="blank"){
			        	tWarning("값을 모두 입력해 주세요.");
			        }else if($.trim(msg)=="notcode"){
			        	tWarning("등록되지 않은 자재 코드가 존재 합니다.\n 자재 코드를 확인해 주세요.");
			        }else if($.trim(msg)=="error"){
			        	tError("저장에 실패 하였습니다.");		        	
			        }else {
			        	console.log(msg);
			        }
			 },
			 error:function(msg){
				   	tError("데이터 등록 오류 입니다.");
				   	console.log(msg);
			 }
		});  			
		
	}else{
		tWarning("데이터를 입력해 주세요.");
	}
}





/**
 * 
 * 
 *  자재  - 재고관리
 * 
 * 
 * 
 */
function stockAjaxSearchList(page){
	$('#Progress_Loading').show(); //ajax실행시 로딩바를 보여준다.
	var mcode=$("#mcode").val();
	var materialName=$("#materialName").val();	
	$.ajax({		
		url:"stockAjaxSearchList.do",
		type:"post",
		data:{
			'mcode':mcode,
			'materialName':materialName,
			'page':page
		},
		dataType:"json",		
		success:function(result){	
			
			$("#stockTotalCount").text(numberWithCommas(result.totalCount));			
			
			//더보기
			if(result.stockList.length >0){			
				//핸들바 템플릿 가져오기
				var source = $("#stockTemplate").html();
				var stockTemplate = Handlebars.compile(source);
				//배열 형식 이라 자동으로 매칭							 
				$("#stock_search_tbody").append(stockTemplate(result.stockList));	
			
				if(result.page <(result.tempEndPage)){				
					var more="<button class='btn btn-primary'	onclick='stockAjaxSearchList("+(parseInt(result.page)+1)+")'>더보기</button>";							
					$("#stock_pagining").html(more);								
				}else{
					$("#stock_pagining").html("");
				}
			}else{
				
				$("#stock_search_tbody").html('<tr><td class="text-center" colspan="6">조회 결과가 없습니다. 해당 자재코드로 입고신청해 주세요. </td></tr>');
				
				$("#stock_pagining").html("");	
			}
			
			$('#Progress_Loading').hide(); //ajax종료시 로딩바를 숨겨준다.
		},
		error:function(result){
			console.log(result);			
			$('#Progress_Loading').hide(); //ajax종료시 로딩바를 숨겨준다.
		}		
	});
}



function stockSearch(){
	$("#stock_search_tbody").html("");	
	$("#stock_pagining").html("");	
	stockAjaxSearchList(1);	
}


function stockChangeButton(maid){
	$(".input-"+maid).addClass("material-input3");
	$(".input-"+maid).attr("readonly", false); 
	$(".input-"+maid).val( $(".input-"+maid).val().replace(/[^0-9]/gi,"") );
	$("#up-"+maid).show();
	$("#ch-"+maid).hide();
}

function stockUpdate(mcode, maid){	
	var mcode=$("#mcode-"+maid).val();
	var materialName=$("#materialName-"+maid).val();	
	var	properInventory=$("#properInventory-"+maid).val().replace(/,/g, '');
	var currentInventory=$("#currentInventory-"+maid).val();
		
	if(currentInventory==""){
		tWarning("현재고를 입력해 주세요.");
		$("#currentInventory-"+maid).focus();
		return;
	}
	
	if(parseInt(currentInventory)> parseInt(properInventory)){
		tWarning("적정 재고수량을 초과 입력했습니다.");
		return;
	}
	
	$.ajax({		
			url:"stockUpdate.do",
			type:"post",
			data:{
				'maid':maid,
				'currentInventory':currentInventory
			},
			dataType:"text",		
			success:function(result){				
				if($.trim(result)=="success"){
					$("#stock_search_tbody").html("");
					stockAjaxSearchList(1);								
					tSuccess("수정 되었습니다.");
				}else if($.trim(result)=="error"){
					tError("수정에 실패 하였습니다.");
				}else {
					tWarning("수정에 실패 하였습니다.");
				}
			},
			error:function(result){
				console.log(result);
				tError("서버 오류 입니다.");
			}
	}); 
 
}







/**
 * 
 * 
 * 자재불출 신청
 * 
 * 
 */

function getByMcodeAutoCompleateBulchul(event){
	
	if($(event).val()!=null && $(event).val()!=""){
		$.ajax({
			url:'getBySearchList.do',
			data:{
				"mcode":$(event).val()
			},
			type:"post",
			dataType:"json",
			success:function(result){			
				if(result.materialList.length>0){
					var html="";
					$.each(result.materialList, function(index, item){				
						html +='<option value="'+item.mcode+'">';				
					});					
					$("#mcode-list").html(html);
							
				}else{
					$("#mcode-list").html("");
				}							
			},
			error:function(result){
				console.log(result);
			}		
		});   
	}
}

function ttAutocompleteBulchulApply(e){
	var mcode=$(e).val();
	var home=$("#HOME").val();	
	var properInventory=$("#properInventory").val();
			
	$.ajax({
		url:"getByMcodeInfo.do",
		data:{'mcode':mcode},
		type:"post",
		dataType:"json",
		success:function(data){			
			if(data.materialInfo){				
				$("#maid").val(data.materialInfo.maid);
				$("#currentInventory").val(data.materialInfo.currentInventory);				
				$("#materialName").val(data.materialInfo.materialName);
				$("#properInventory").val(data.materialInfo.properInventory);
				$("#applicationQuantity").attr("max", data.materialInfo.properInventory);
				$("#applicationQuantity").attr("data-original-title" ,"적정재고수량 :"+data.materialInfo.properInventory);
				$("#applicationQuantity").tooltip({
					placement: 'top'
				});					
			}

		},
		error:function(result){
			console.log(result);
		}
							
	});
	
}


function addBulchulApply(){
	var no= parseInt($("#no").val());
	var applicationQuantity=$("#applicationQuantity").val();
	var properInventory=$("#properInventory").val();
	
	var mcode=$("#mcode").val(); //2.자재코드
	var materialName=$("#materialName").val(); //3.자재명
	var currentInventory=$("#currentInventory").val();	 //4.재고수량
	var applicationQuantity=$("#applicationQuantity").val();//5.신청수량
	
	if(mcode==""){
		tWarning("자재코드를 입력해 주세요.");
		$("#mcode").focus();
		return;
	}
	
	if(materialName==""){
		tWarning("자재명을 입력해 주세요.");
		$("#materialName").focus();
		return;
	}
	
	if(applicationQuantity==""){
		tWarning("신청 수량을 입력해 주세요.");
		$("#applicationQuantity").focus();
		return;
	}
	
	if(parseInt(applicationQuantity)  <1){
		tWarning("신청 수량을 1이상 입력해 주세요.");
		$("#applicationQuantity").focus();
		return;
	}
	
	if(parseInt(applicationQuantity) > parseInt(properInventory)){
		tWarning("적정재고수량을 초과 할 수 없습니다.");
		$("#applicationQuantity").focus();
		return;
	}	
		
	
	
	var html="";

	html +="<tr id='list_"+no+"'>";
	html +="<td class='text-center'>"+(no+1)+"</td>";					
	html +="<td class='text-right'><input type='text' id='list_"+no+"_mcode' name='list["+no+"].mcode' value='"+mcode+"' class='material-input2 ' ";
	html +=" readonly >";
	html +="</td>";
	html +="<td class='text-right'><input type='text' id='list_"+no+"_materialName'  name='list["+no+"].materialName' value='"+materialName+"' class='material-input2 ' readonly ></td>";
	html +="<td class='text-right'><input type='text' id='list_"+no+"_currentInventory'  name='list["+no+"].currentInventory' value='"+currentInventory+"' class='material-input2 ' readonly ></td>";
	
	html +="<td class='text-right'><input type='text' id='list_"+no+"_applicationQuantity' name='list["+no+"].applicationQuantity' value='"+applicationQuantity+"' class='material-input2 ' numberonly='true'  readonly ";
	html +=" data-toggle='tooltip' data-placement='top'  ></td>";																						
			
	html +="<td class='text-right'><input type='text' id='list_"+no+"_remarks' name='list["+no+"].remarks'  class='form-control' ></td>";
	html +="</tr>";
	
	$(".blank").remove();
	$("#tbody-bulchul-apply").prepend(html);
	$("#mcode").val("");		
	$("#materialName").val("");
	$("#applicationQuantity").val("");
	$("#currentInventory").val("");
	
	$("#applicationQuantity").attr("data-original-title" , "");
	$("#applicationQuantity").tooltip("hide");
	no++;
	$("#no").val(no);
}


function bulchulApplySave(){

	var formdata = $("#bulchulApplyFRM").serialize();
	$.ajax({
		 url: 'bulchulApplyWrite.do',
		 type: 'POST',
		 data:formdata,
		 dataType: 'text',
		 success: function(msg) {				        
			   if($.trim(msg)=="success"){
				   $("#no").val("0");
				   $("#tbody-bulchul-apply").html('<tr class="blank"><td colspan="6" class="text-center">데이터를 입력해 주세요.</td></tr>');
		        	tSuccess("불출신청 되었습니다.");	
			    }else if($.trim(msg)=="notcode"){
		        	tWarning("등록되지 않은 자재 코드가 존재 합니다.\n 자재 코드를 확인해 주세요.");		        
		        }else{
		        	tError("등록에 실패 하였습니다.");
		        	console.log(msg);
		        }
		 },
		 error:function(msg){
			   	tError("데이터 등록 오류 입니다.");
			   	console.log(msg);
		 }
	});  			
			
}





/**
 * 
 * 
 * 자재불출 처리
 * 
 * 
 */
function bulchulDefaultchList(page){
	$('#Progress_Loading').show(); //ajax실행시 로딩바를 보여준다.	
	$.ajax({		
		url:"bulchulProcess.do",
		type:"post",
		data:{
			'processStatus':'0',
			'page':page
		},
		dataType:"json",		
		success:function(result){	
			$("#bulchulListTotalCount").text(numberWithCommas(result.totalCount));			
			
			//더보기
			if(result.bulchulList.length >0){			
				//핸들바 템플릿 가져오기
				var source = $("#template").html();
				var template = Handlebars.compile(source);
				
				Handlebars.registerHelper("counter", function (index){
				    return index;
				});
				
				//배열 형식 이라 자동으로 매칭							 
				$("#bulchul_search_tbody").append(template(result.bulchulList));	

			
				
			 	 $('#bulchul-table').DataTable( {
				 		"colReorder": true,
				        "paging":   false, 			        
				        "info":     false,
				        "searching":     true,
				        
				        "scrollCollapse": false,
				        "pageLength": 300,
				        "order": [ [ 0, "desc" ] ],
				        "lengthChange": false,				      							        
				  });

				 $("input[type='search']").attr("placeholder", "검색");
				
			
				
			}else{				
				$("#bulchul_search_tbody").html('<tr><td class="text-center" colspan="8">조회 결과가 없습니다.</td></tr>');				
				$("#bulchul_search_pagining").html("");	
			}
			
			$('#Progress_Loading').hide(); //ajax종료시 로딩바를 숨겨준다.
		},
		error:function(result){
			console.log(result);
			
			$('#Progress_Loading').hide(); //ajax종료시 로딩바를 숨겨준다.
		}		
	});
}


function bulchulDefaultchList2(page){
	$('#Progress_Loading2').show(); //ajax실행시 로딩바를 보여준다.	
		
	
	$.ajax({		
		url:"bulchulProcess.do",
		type:"post",
		data:{
			'processStatus':'1',
			'page':page
		},
		dataType:"json",		
		success:function(result){	
			
			$("#bulchulListTotalCount2").text(numberWithCommas(result.totalCount));			
			
			//더보기
			if(result.bulchulList.length >0){			
				//핸들바 템플릿 가져오기
				var source = $("#template2").html();
				var template = Handlebars.compile(source);
				//배열 형식 이라 자동으로 매칭							 
				var data=$("#bulchul_search_tbody2").append(template(result.bulchulList));	
			

				 
			 	 $('#bulchul-table-2').DataTable( {
				        "paging":   false, 			        
				        "info":     false,
				        "searching":     true,
				        "scrollY":        420,
				        "scrollCollapse": false,
				        "pageLength": 300,
				        "order": [ [ 0, "desc" ] ],
				        "lengthChange": false,				      							        
				  });
 
				 $("input[type='search']").attr("placeholder", "검색");
				
				
				
			}else{				
				$("#bulchul_search_tbody2").html('<tr><td class="text-center" colspan="9">생산 계획 자재가 없습니다.</td></tr>');				
				$("#bulchul_search_pagining2").html("");	
			}
			
			$('#Progress_Loading2').hide(); //ajax종료시 로딩바를 숨겨준다.
		},
		error:function(result){
			console.log(result);
			
			$('#Progress_Loading2').hide(); //ajax종료시 로딩바를 숨겨준다.
		}		
	});
}


function bulchulStatusSave(){
	var blankConfirm=$("#bulchulPlanSaveFRM input[name=blankConfirm]").length
	
	
	if(blankConfirm===0){
		tWarning("저장할 데이터가 없습니다.");
		return;
	}
	
	
	toastr.remove();
	toastr.clear();
	var content="저장 하시면 더이상 생산 계획 수정, 삭제를 할 수 없습니다. 저장 하시겠습니까?";
	toastr.info(
		"<div class='text-center'><button type='button' id='confirmationRevertYes' class='btn btn-success btn-custom2'>&nbsp;&nbsp;예&nbsp;&nbsp;</button>" +
		"&nbsp;&nbsp;&nbsp;<button type='button' id='confirmationRevertNo' class='btn btn-danger btn-custom2'>아니오</button><div>" ,
		content,				
	  {
	      closeButton: false,
	      allowHtml: true,
	      onShown: function (toast) {
	          $("#confirmationRevertYes").click(function(){
	        	
	        	  $.ajax({		
	        			url:"bulchulStatusSave.do",
	        			type:"post",	        			
	        			dataType:"text",		
	        			success:function(result){
	        				if($.trim(result)=="success"){
	        					location.reload();
	        				}
	        			},
	        			error:function(result){
	        				console.log(result);
	        			}
	        		});
	          });	         
	          $("#confirmationRevertNo").click(function(){	        	  
	        		toastr.remove();
	        		toastr.clear();
		      });	          
	        }
	  });
	
	
}

//불출 처리
function bulchulProcessSave(){	
	var formdata = $("#bulchulProcessFRM").serialize();
	$.ajax({
		 url: 'bulchulProcessSave.do',
		 type: 'POST',
		 data:formdata,
		 dataType: 'json',
		 success: function(result) {				        
			 			
			 if(result.message=="success"){				 
				 location.href="bulchulProcess.do?message=success";
			 }else if(result.message=="empty"){
				 tWarning("불출 처리할 데이터가 없습니다.");  
				 
			 }else if(result.message=="error"){
				 tError("불출 처리 오류 입니다.");				 
				 $.each(result.errorList,  function(index, data){
					 console.dir(data);
					 $("#errorMessage").append("<p>"+data.no+" 번의 자재코드 "+data.mcode+" 는 "+data.msg+"</p>");	 
					 $("#tr-"+data.bulchulId).css("border", "2px solid #d2322d");			
					 $("#bulchulQuantity-"+data.bulchulId).val(data.bulchulQuantity);
					 $("#bulchulCheckbox-"+data.bulchulId).attr("checked");
				 });
				 	
				 
				 $("#errorMessage").append("<p><button class='btn btn-danger' type='button' onclick='javascript:location.reload()'>확인</button></p>");
			 }else{
				 tError("불출 처리 오류 입니다.");
				 console.dir(result);
			 }

		 },
		 error:function(msg){
			   	tError("데이터 등록 오류 입니다.");
			   	console.log(msg);
		 }
	});  			
}

function checkBulchulCount(event){
	var bulchulId=$(event).attr("data-id");
	var properInventory=parseInt($("#properInventory-"+bulchulId).val()); //적정재고수량
	var currentInventory=parseInt($("#currentInventory-"+bulchulId).val()); //현재 재고수량
	var applicationQuantity=parseInt($("#applicationQuantity-"+bulchulId).val()); //신청한 자재 수량
	var bulchulQuantity=$(event).val(); //불출 신청수량
	
	if($.trim(bulchulQuantity)!=""){
		bulchulQuantity=parseInt(bulchulQuantity);
		if(bulchulQuantity>0){
			
			if(bulchulQuantity >properInventory){
				tWarning("적정재고수량보다 초과 입력 하셨습니다. 적정재고수량을 확인해 주세요.");
				$(event).val("");
				$(event).focus();
				$(event).addClass('animated shake');
				setTimeout(function(){
					$(event).removeClass('animated shake');
				}, 2000);
				return;
			}
			
			if(bulchulQuantity >currentInventory){
				tWarning("현재 재고수량보다 초과 입력 하셨습니다. 재고수량을 올려주세요.");
				$(event).val("");
				$(event).focus();
				$(event).addClass('animated shake');
				setTimeout(function(){
					$(event).removeClass('animated shake');					
				}, 2000);
				return;
			}
			
			if(bulchulQuantity >applicationQuantity){
				tWarning("신청 수량보다 초과 입력 하셨습니다.");
				$(event).val("");
				$(event).focus();
				$(event).addClass('animated shake');
				setTimeout(function(){
					$(event).removeClass('animated shake');
				}, 2000);
				return;
			}

		}				
	}
	
}












