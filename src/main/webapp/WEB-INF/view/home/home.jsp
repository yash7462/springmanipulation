<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manipulation Demo</title>
<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
   <link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css">
   <link rel="stylesheet" href="<%=request.getContextPath()%>/css/all01.css">
   <link rel="stylesheet" href="<%=request.getContextPath()%>/css/toastr.css">
   <script src="<%=request.getContextPath()%>/js/toastr.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/custom.css">
  </head>
  <body>
  <div class="testbox1" style="display: flex;
    justify-content: space-evenly;">
    <div class="testbox" style="justify-content: space-evenly;">
    
      <form id="stringmaniform" action="/checkmanipulation">
        <h1>Choose String Manipulation You Want</h1>
        <p class="small">Please Write Your Great Sentences/Quotes.</small></p>
          <textarea id="actualString" name="actualString" rows="5" value="write here your paragraph"></textarea>
          <div class="title-block" >
	        <h4>Select Manipulation </h4>
	        <h4>Select Based On</h4>	          
          </div>
          <div class="title-block" >
	        <select name="manipulation" id="manipulation" onchange="warningmsg()">
	          <option value="" selected="selected">Select Manipulation</option>
	          <option value="truncate">Truncate</option>
	          <option value="reverse">Reverse</option>
	          <option value="upparcase">UPPER-CASE</option>
	          <option value="lowercase">lower-case</option>
	          <option value="count">count</option>
	        </select>
	        <select name="basedon" id="basedon">
	          <!-- <option value="" >Select Based On</option> -->
	          <option value="word" selected="selected">Word</option>
	          <option value="character">Character</option>
	        </select>
	          
          </div>
          
        
        <h4>Selection of n number of digit operation</h4>
        <div class="title-block">
	        <select name="frequency" id="frequency">
	          <option value="" selected="selected">Select Frequency</option>
	          <option value="first">first</option>
	          <option value="last">last</option>
	        </select>
	          <input class="name" type="number" name="digit" id="digit"  placeholder="Select Digit" />
          	<select name="action" id="action ">
		          <option value="" selected="selected">Select Digit Actions</option>
		          <option value="exclued">Exclued</option>
		          <option value="inclued">Inclued</option>
	        </select>
	        
          </div>
        
        
        <div class="btn-block">
          <button type="button" id="check">Check</button>
        </div>
      </form>
    </div>
    <div class="testbox" style="justify-content: space-evenly;">
    <form id="outputform">
    <h1>Output--------------------------------------------------</h1>
    
    <textarea id="actualstringresult"  rows="10"></textarea>
    	<textarea id="output"  rows="10"></textarea>
    	</form>
    </div>
    </div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	  $("#digit").keydown(function(e){
		  var allowedKeyCodesArr = [9, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 8, 37, 39, 109, 189, 46, 110, 190];  // allowed keys
		    if ($.inArray(e.keyCode, allowedKeyCodesArr) === -1) {  // if event key is not in array and its not Ctrl+V (paste) return false;
		        e.preventDefault();
		    } //else if ($.trim($(this).val()).indexOf('.') > -1 && $.inArray(e.keyCode, [110, 190]) !== -1) {  // if float decimal exists and key is not backspace return fasle;
		      //  e.preventDefault();
		    //} 
		    else {
		        return true;
		    }
	  });


      $("#check").click(function(e){
    	  $.ajax({
              url: $("#stringmaniform").attr("action"),
              cache: false,
              data: $("#stringmaniform").serialize(),
              type: 'POST',
              success: function (data) {
    			console.log(data);
    			$('#actualstringresult').val(data.manipulatedString);
				$('#output').val(data.result);
                  
              }, error: function (error) {
                 console.log(error);
                  
              }

         });
      });
      
	  
	});
	function warningmsg(){
		if($('#manipulation').val()=='truncate'){
			toastr.options = {
					  "closeButton": true,
					  "debug": false,
					  "newestOnTop": false,
					  "progressBar": false,
					  "positionClass": "toast-top-center",
					  "preventDuplicates": true,
					  "onclick": null,
					  "showDuration": "300",
					  "hideDuration": "1000",
					  "timeOut": "5000",
					  "extendedTimeOut": "1000",
					  "showEasing": "swing",
					  "hideEasing": "linear",
					  "showMethod": "fadeIn",
					  "hideMethod": "fadeOut"
					};
			toastr.warning("","Please Select frequency , digit and action")
		}
	}
function check(e, c) {

    var allowedKeyCodesArr = [9, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 8, 37, 39, 109, 189, 46, 110, 190];  // allowed keys
    if ($.inArray(e.keyCode, allowedKeyCodesArr) === -1) {  // if event key is not in array and its not Ctrl+V (paste) return false;
        e.preventDefault();
    } else if ($.trim($(c).val()).indexOf('.') > -1 && $.inArray(e.keyCode, [110, 190]) !== -1) {  // if float decimal exists and key is not backspace return fasle;
        e.preventDefault();
    } else {
        return true;
    }
}
</script>
</html>