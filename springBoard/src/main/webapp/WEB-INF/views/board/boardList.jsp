<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>list</title>
</head>
<script type="text/javascript">

	$j(document).ready(function(){
        
		$j("#allCheck").on("click", function(){
			
			//체크여부 확인
		     if ($j(this).is(":checked")) {
	        // 체크된 경우: name="boardTypeList"인 체크박스들의 id 값을 selectedValues 배열에 추가
	        $j("input[name='boardTypeList']").each(function() {
	            let id = $j(this).attr('id');
	            // 중복 체크: 이미 배열에 없는 경우만 추가
	            if (!selectedValues.includes(id) && id !== "allCheck") {
	                selectedValues.push(id);
	            }
	        });
		    } else {
		        // 전체선택 체크박스가 해제된 경우: 배열에서 모든 id 값 제거
		        $j("input[name='boardTypeList']").each(function() {
		            const id = $j(this).attr('id');
		            const index = selectedValues.indexOf(id);
		            if (index > -1) {
		                selectedValues.splice(index, 1);  // 배열에서 해당 id 제거
		            }
		        });
		    }
            
            
			$j("input[name='boardTypeList']").prop("checked", $j(this).is(":checked"));	
            //alert("id: " + $j(this).attr('id'));
            selectedValues.push($j(this).attr('id'));
            //alert("selectedValues: " +  selectedValues);
		});
		
		//체크된 갯수가 selectedValues.length 보다 작을시 전체 선택 표시 없애기  
		const ckAll = document.querySelector(".ck-all");
		const ckArr = document.querySelectorAll(".ck"); 
		//$('input:checkbox[name="boardTypeList"]').length//체크박스 전체 갯수 

	
		ckAll.addEventListener("click", () =>{
			
			ckArr.forEach(ck => {
				ck.checked = ckAll.checked;
			})
			
		})
		
		ckArr.forEach(ck =>{
			ck.addEventListener("click",() =>{
				
				let cnt = 0;
				
				ckArr.forEach(ck =>{
					if(ck.checked == true){
						
						cnt++;
					}
				})
				
				if(cnt == ckArr.length){
					//ckArr.length // ('input:checkbox[name="boardTypeList"]')
					ckAll.checked = true;
				} else {
					ckAll.checked = false;
				}
				
			})
		})

		
		var selectedValues = [];//선택된 체크박스 id값을 넣은 배열
		
		$j("input[name='boardTypeList']:checked").each(function() {
			//selectedValues = [];
			
	        selectedValues.push($j(this).attr('id'));  // (체크된) 체크박스의 id 값 가져오기 (codeName)
	        
	    });
		
		$j("input[name='boardTypeList']").on("click", function(){//각 체크박스 선택했을시 id값
			let value = $j(this).val();
			//alert("value값은: " + value); 
			
			let id = $j(this).attr('id');
		    //alert("id 값은: " + id);
		    
		    //체크여부 확인
		    if($j(this).is(":checked")){
		    	selectedValues.push($j(this).attr('id'));//체크시 추가 아닐시 배열에서 뺌 
		    } else {
		    	const index = selectedValues.indexOf(id);
		    	
		    	if (index > -1) {
		            selectedValues.splice(index, 1);  // 배열에서 해당 id 제거
		        }
		    }

	        //alert("selectedValues: " +  selectedValues);
			
		});
	
		//폼메서드 없이 아작스로 바꾼다면? 
		$j("#searchBtn").on("click", function(){
			
			
			 // 선택된 체크박스의 값
			 let selectedBoardTypes = [];

			 $j("input[name='boardTypeList']:checked").each(function() {
					
			   // (체크된) 체크박스의 id 값 가져오기 (codeName)
			      let val = $j(this).val();
		          if (val !== "") {  // 빈 값이 아닐 때만 추가
		            selectedBoardTypes.push(val);
		          }
			  });
			
			 alert("selectedBoardTypes " + selectedBoardTypes);
			 
			 let url = "/board/boardList.do?";
			 
			
			 if (selectedBoardTypes.length > 0) {
			   
			     url = "/board/boardAjaxList.do?boardTypeList=" + selectedBoardTypes;
			  } else {
				  location.href="/board/boardList.do";
			  }
			 
			 //아작스 요청 
			 $j.ajax({
				 url: url,
				 dataType: "json",
				 type: "GET",
				 //data : {boardTypeList : selectedBoardTypes},//일반적으로 post 요청에 필요//// 체크박스의 선택된 값들
				 success: function(data, textStatus, jqXHR)
				 {//성공시 뭘불러올지? 
					let html = '';
				 	let html2 = '';
	 				//alert("성공" + textStatus + jqXHR);	
	 				//alert("메세지:"+data.success);
	 				
	 				console.log("토탈카운트" + data.totalCnt);
	 				console.log("페이지" + data.pageNo);
	 				console.log("보드리스트" + data.boardList);
	 				console.log("코드네임리스트" + data.codeNameList);
	 				//location.href = "/board/boardList.do?boardTypeList=" + selectedBoardTypes;//리로드하는 방식

	 				//토탈카운트
				 	$j("#totalCnt").text('total : ' + data.totalCnt);
				 	
				 	//보드리스트
					if(data.boardList != null){
						
						$j("#boardTable").html('');//기존 뷰에 뿌려진 데이터 날리기
						
						html += `<tr id="tableHeader">
			                <td width="80" align="center">Type</td>
			                <td width="40" align="center">No</td>
			                <td width="300" align="center">Title</td>
			                <td width="75" align="center"></td>
			            </tr>`;
						/*
						data.boardList.forEach((item)=>{
							//html+=boardTableTr(item);
							
							
							console.log("item" + item);
						});*/
					
					data.boardList.forEach(function(item){
						 	console.log("item:", item);  
						    console.log("item.codeName:", item.codeName);
						    console.log("item.boardNum:", item.boardNum);
						    console.log("item.boardTitle:", item.boardTitle);
						    console.log("item.boardType:", item.boardType);
						    console.log("item.pageNo:", item.pageNo);		
							html+=  `<tr>
				                        <td align="center">` +item.codeName+ `</td>
				                        <td>` +item.boardNum+ `</td>
				                        <td>
				                            <a href="/board/` +item.boardType + `/` + item.boardNum + `/boardView.do?pageNo=${pageNo}">` + item.boardTitle + `</a>
				                        </td>
				                        <td>
				                            <button class="deleteBtn" data-board-num=` + item.boardNum + ` data-board-type= ` + item.boardType + `>삭제 버튼</button>
				                        </td>
				                    </tr>`;
					});		
				
			 			$j("#boardTable").html(html);
	
						console.log("출력화면" + html);
						
						$j("#boardTable").on("click", ".deleteBtn", function() {
						    let boardNum = $j(this).data("board-num");
						    let boardType = $j(this).data("board-type");

						    var result = confirm("삭제 하시겠습니까?");
						    if (!result) {
						        return;
						    }
						    
						    // AJAX 삭제 요청
						    $j.ajax({
						        url: '/board/boardDeleteAction.do',
						        type: 'GET',
						        data: { boardNum: boardNum, boardType: boardType },
						        success: function(data) {
						            if (data.success) {
						                alert("게시글이 삭제되었습니다.");
						                // 해당 게시글을 리스트에서 제거
						                $j(this).closest('tr').remove();
						            } else {
						                alert("삭제 실패");
						            }
						        },
						        error: function() {
						            alert("오류 발생");
						        }
						    });
						});
	
					} else {
						$j("#boardTable").html('<tr><td colspan="4" align="center">조회 결과가 없습니다.</td></tr>');
						$j("#boardTable").html(html);
				        $j("#totalCnt").text('total : 0');
					}	
					$j("#boardTable").on("click", ".deleteBtn", function() {
			            const boardNum = $j(this).data("board-num");
			            const boardType = $j(this).data("board-type");

			            console.log("삭제할 게시물 번호:", boardNum);
			            console.log("게시물 타입:", boardType);

			        });
				
				 },
				 error: function(jqXHR, textStatus, errorThrown)
				{
					 alert("실패" + textStatus +", " + errorThrown);	
					 console.log("실패" + textStatus + "," + errorThrown);
					 alert("상태 코드:"+ jqXHR.status);
					 console.log("상태 코드:"+ jqXHR.status);
					alert("응답 텍스트:", jqXHR.responseText);
					console.log("응답 텍스트:"+ jqXHR.responseText);
					alert("AJAX 요청 URL:"+ url);
					console.log("AJAX 요청 URL:"+ url);
					alert("선택된 보드 타입:"+ selectedBoardTypes);
					console.log("선택된 보드 타입:"+ selectedBoardTypes);
				}
				 
				 
			 });
			 
		});

		
		$j(".deleteBtn").on("click",function(){
			
			let boardNum = $j(this).data("board-num");
			let boardType = $j(this).data("board-type");
			//alert("boardNum :" + boardNum);
			
			var result = confirm("삭제 하시겠습니까?");
	
			if(result){
				
			} else {
				return ;
			}
			
			//alert("boardNum :" + boardNum);
			
			$j.ajax({
				url : "/board/boardDeleteAction.do",
				type:"GET",
				data : {boardNum: boardNum,boardType: boardType },
				success:function(data){
					
					alert("삭제완료");
					
					alert("메세지:"+data.success);
					
					location.reload();
					
				}
				, error: function(){
					alert("삭제 실패");
				}
		
			});
			
		});
	
	});

</script>
<body>

<table align="center">
	<tr bgcolor="">
		 
		<td width="" style="text-align: left;">
		<c:choose>
			<c:when test="${not empty userId }">
				${userName}님
			</c:when>
			<c:otherwise>
				<a href="/board/loginPage.do">login</a>
				<a href="/board/joinPage.do">join</a>
			</c:otherwise>
		</c:choose>	
			<span id="totalCnt" style="float: right;">
			total : ${totalCnt}
			</span>
		</td>
	
	</tr>
	<tr bgcolor="">
		<td>
			<table id="boardTable" style="width: 100%;" border = "1">
				<tr id="tableHeader">
					<td width="80" align="center">
						Type
					</td>
					<td width="40" align="center">
						No
					</td>
					<td width="300" align="center">
						Title
					</td>
					<td width="75" align="center">
						
					</td>
				</tr>
				<c:forEach items="${boardList}" var="item">
					<tr id="boardTableTr">
						<td align="center">
							${item.codeName}
						</td>
						<td>
							${item.boardNum}
						</td>
						<td>
							<a href = "/board/${item.boardType}/${item.boardNum}/boardView.do?pageNo=${pageNo}">${item.boardTitle}</a>
						</td>
						<td>
							<button class="deleteBtn" data-board-num="${item.boardNum }" data-board-type="${item.boardType}">삭제 버튼</button>
						</td>
					</tr>	
				</c:forEach>
			</table>
		</td>
	</tr>
	
	<tr>
		<td style="text-align: right;">

			<a href ="/board/boardWrite.do">글쓰기</a>
			
			<c:choose>
				<c:when test="${not empty userId }">
					<a href="/board/logoutAction.do">로그아웃</a>
				</c:when>
				<c:otherwise>
					
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	
	<tr id="codeNameList"bgcolor="">
		 
		<td style="text-align: left;">
			<!--  <form action = "/board/boardList.do" method="get">-->
				<label><input type="checkbox" id="allCheck" class="ck-all"/>전체</label>
				<c:forEach items="${codeNameList}" var="list">
				<c:choose>
					<c:when test= "${list.codeType eq 'menu' }">
					<label id="checkboxlist"><input type="checkbox" id="${list.codeName}" class="ck" name="boardTypeList" value="${list.codeId}"/>
							${list.codeName } 
					</label>
					</c:when>
				</c:choose>		
				</c:forEach>
				<button id="searchBtn">조회</button>
				<!-- <button type="submit" id="searchBtn">조회</button>-->
				
			<!--  </form>	-->		
		</td>
		
	</tr>
</table>	
</body>
</html>