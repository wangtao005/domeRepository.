var grid ;
$(function(){
	var islist = $("#fileUpload").attr("islist")=="true"?true:false; 
	if(islist){
		grid = mini.get("datagrid1");
		grid.hideColumn ("id");
//	grid.hideColumn ("file_size");
//	grid.hideColumn ("file_type");
		grid.hideColumn ("file_url");
	}
}); 
	


 var fileList={};//新增
 var viewFiles={};//查看用
 var fileNum= 0;
 var delIds="";
 var mytype;
 var islist;
 layui.use('upload', function(){
	 
    islist = $("#fileUpload").attr("islist")=="true"?true:false; 
	 mytype = $("#fileUpload").attr("data-multifile")=="false"?false:true;
	 var filetype = $("#fileUpload").attr("data-type");
 
   var upload = layui.upload;
   //执行实例
   var uploadInst = upload.render({
     elem: '#fileUpload', //绑定元素
     url: '/file/fileUpload', //上传接口
     accept:"file",//指定允许上传时校验的文件类型，可选值有：images（图片）、file（所有文件）、video（视频）、audio（音频）
     multiple:mytype,//是否允许多文件上传
     exts:filetype?filetype:"",
     allDone: function(obj){ //当文件全部被提交后，才触发
     	openAlert("上传成功");
       },
     done: function(res, index, upload){
       //上传完毕回调
       if(res.status){
 	      var fileArr={};
 	      fileArr.fileName = res.fileName;
 	      fileArr.fileSize = res.fileSize;
 	      fileArr.fileType = res.fileType;
 	      fileArr.fileUrl = res.path;
 	      fileList[index]=fileArr;
 	      if(islist){
 	    	 var  row = newRow();
 	   	      grid.cancelEdit();
 	   	      //操作列
	 	   	  var d = '<a class="mini-button" style="color:black;text-decoration:none;" onclick="delRow(\''+index+'\')">&nbsp&nbsp删除&nbsp&nbsp</a>';
	          var  html= '&nbsp&nbsp&nbsp<a class="mini-button" style="color:black;text-decoration:none;" onclick="show(\''+index+'\')">&nbsp&nbsp预览&nbsp&nbsp</a>&nbsp&nbsp&nbsp'+d;
	 		      
 	   	      grid.updateRow(row,{"file_name":res.fileName,"file_size":res.fileSize+"kb","file_url":res.path,"file_type":res.fileType,"action":html});
 		      
 	      }else{
 	     
 	 	    if(!mytype){
     	 	   //单文件上传,只能有一个文件
     	 	 $("#fileUpload").hide();
     	    }
	 	     fileNum++;
	 	 	 var span = $("#file_show");
	 	 	 var type = res.fileType;
	 	 	var img = typeinfo(type);
	 	 	 
				if(fileNum<2){
					var a="<div id='"+index+"' style='text-align: center;display: inline-block;'> "
							+"<a><span style='color:red;position: absolute;top: -10;right: -10;z-index: 99;cursor:hand;position: relative;left: 22px;top: 10px;' onclick=\"delFile('" +index+"')\">X</span></a></br>"
							+"<a><img alt=''style='width: 50px;' src='/image/"+img+"' onclick=\"show('"+index+"')\"></a></br>"
							+"<a class='file-name' >" + res.fileName + "</a>"
							+"</div>";
				}else{
					var a="<div id='"+index+"' style='margin-left:50px;text-align: center; display: inline-block;'>"
							+"<a><span style='color:red;position: absolute;top: -10;right: -10;z-index: 99;cursor:hand;position: relative;left: 22px;top: 10px;' onclick=\"delFile('" +index+"')\">X</span></a></br>"
							+"<a><img style='width: 50px;' alt='' src='/image/"+img+"' onclick=\"show('"+index+"')\"></a></br>"
							+"<a class='file-name' >" + res.fileName + "</a>"
							+"</div>";
				}
				span.append(a);
			  
 	      }
       }else{
     	  openAlert("上传失败");
       }
       console.log(fileList);
     },
     error: function(){
       //请求异常回调
     	openAlert("上传失败");
	     }
	   });
	 });
 
 function openAlert(text){
 	 layer.open({
 	        type: 1,
 	        offset: 'auto',
 	        id: 'layerDemo'+'auto', //防止重复弹出
 	        content: '<div style="padding: 20px 100px;"> '+text+'</div>',
 	        btn: '关闭',
 	        btnAlign: 'c', //按钮居中
 	        shade: 0, //不显示遮罩
 	        yes: function(){
 	          layer.closeAll();
 	        },
 	      });
 }
//下载
 function downFile(id){
	 mini.confirm("确定下载该文件吗？", "确定", function(e){
		 if(e=="ok"){
			 window.location.href="/file/download?id="+id;
		 }
	 });
 }

 function onCancel(){//取消保存表单时删除上传的文件
     $.ajax({
 		type : "post",
 		url : "/file/delFile",
 		data : {fileList:JSON.stringify(fileList)},
 		success : function(msg) {
 			CloseWindow("ok");
 		}
 	});
 }
 //删除
 function delFile(index,type){
	 mini.confirm("确定删除该文件吗？", "确定", function(e){
		 if(e=="ok"){
			 if(type){
				 if(delIds.length>0){
					 delIds=delIds+","+index
				 }else{
					 delIds=delIds+index
				 }
				 $("#"+index).remove();
			 }else{
				 delete fileList[index];
				 $("#"+index).remove();
				 $("#fileUpload").show();
			 }
			 $("#fileUpload").show();
		 }
	 });
 }
 //预览
 function show(index,type){
	 var fileInfo;
	 var myUrl;
	 if(type){
		  fileInfo = viewFiles[index];
		  myUrl = fileInfo.fileUrl;
	 }else{
		  fileInfo = fileList[index];
		  myUrl = fileInfo.fileUrl;
	 }
     //预览
     mini.confirm("确定预览选中记录？", "确定", function(e){
         if(e=="ok"){
             var type = fileInfo.fileType;
             var strImgType = "jpeg,bmp,jpg,png,tif,gif,pcx,tga,exif,fpx,svg,psd,cdr,pcd,dxf,ufo,eps,ai,raw,WMF,webp";//图片
             var url = null;
             var flag = (strImgType.search(type) != -1)?true:false;//是否是图片
             if(flag){
                 var path = encodeURIComponent(myUrl);
                 url = "/file/preview?path="+path;
             }else{
                 var wordText = "doc,docx,txt,xls,xlsx";
                 var path = encodeURIComponent(myUrl);
                 if((wordText.search(type) != -1)?true:false){
                     flag=true;
                     url = "/file/preview?path="+path;
                 }else if("pdf"==type){
                     flag=true;
                     var url1 = encodeURIComponent("/file/preview?path="+path);
                     url = "/pdfjs-2.0.943-dist/web/viewer.html?file="+url1;
                 }else{
                     mini.alert("无法预览该文件!");
                 }
             }
             if(flag){
                 window.open(url,"预览");
             }
         }});
 }
 
 
 
 
 //回显的时候附件
 function editFile(files){
	 if(files&&files.length>0){
		 var islist = $("#fileUpload").attr("islist")=="true"?true:false; 
		 for(var i=0;files[i];i++){
			 viewFiles[files[i].id] = files[i];
			 var res = files[i];
			 
			 if(islist){
				 var  row = newRow();
	 	   	      grid.cancelEdit();
	 	   	      //操作列
		 	   	  var d = '<a class="mini-button" style="color:black;text-decoration:none;" onclick="delRow(\''+files[i].id+'\',\'view\')">&nbsp&nbsp删除&nbsp&nbsp</a>';
		          var  html= '&nbsp&nbsp&nbsp<a class="mini-button" style="color:black;text-decoration:none;" onclick="show(\''+files[i].id+'\',\'view\')">&nbsp&nbsp预览&nbsp&nbsp</a>&nbsp&nbsp&nbsp'+d;
	 	   	      grid.updateRow(row,{"file_name":res.fileName,"file_size":res.fileSize+"kb","file_url":res.fileUrl,"file_type":res.fileType,"action":html}); 
			 }else{
				 var span = $("#file_show");
				 var type = files[i].fileType;
				 var img = typeinfo(type);
				 if(files.length<2){
					 var a="<div id='"+files[i].id+"' style='text-align: center;display: inline-block;'> "
					 +"<a><span style='color:red;position: absolute;top: -10;right: -10;z-index: 99;cursor:hand;position: relative;left: 22px;top: 10px;' onclick=\"delFile('" +files[i].id+"\',\'view\')\">X</span></a></br>"
					 +"<a><img style='width: 50px;' alt='' src='/image/"+img+"' onclick=\"show('"+files[i].id+"\',\'view\')\"></a></br>"
					 +"<a class='file-name' onclick=\"downFile('"+files[i]+"')\">" + files[i].fileName  + "</a>"
					 +"</div>";
				 }else{
					 var a="<div id='"+files[i].id+"' style='margin-left:50px;text-align: center; display: inline-block;'>"
					 +"<a><span style='color:red;position: absolute;top: -10;right: -10;z-index: 99;cursor:hand;position: relative;left: 22px;top: 10px;' onclick=\"delFile('" +files[i].id+"\',\'view\')\">X</span></a></br>"
					 +"<a><img style='width: 50px;' alt='' src='/image/"+img+"' onclick=\"show('"+files[i].id+"\',\'view\')\"></a></br>"
					 +"<a class='file-name' onclick=\"downFile('"+files[i].id+"')\">" + files[i].fileName + "</a>"
					 +"</div>";
				 }
				 span.append(a);
			 }
		 }
	 }
 }
 
 
 //回显的时候附件
 function viewFile(files){
	 if(files&&files.length>0){
		 var islist = $("#fileUpload").attr("islist")=="true"?true:false; 
     for(var i=0;files[i];i++){
         viewFiles[files[i].id] = files[i];
         
    	 var res = files[i];
		 if(islist){
			 var  row = newRow();
 	   	      grid.cancelEdit();
 	   	      //操作列
	          var  html= '&nbsp&nbsp&nbsp<a class="mini-button" style="color:black;text-decoration:none;" onclick="show(\''+files[i].id+'\',\'view\')">&nbsp&nbsp预览&nbsp&nbsp</a>&nbsp&nbsp&nbsp';
 	   	      grid.updateRow(row,{"file_name":res.fileName,"file_size":res.fileSize+" kb","file_url":res.fileUrl,"file_type":res.fileType,"action":html}); 
 	   	      $(".mini-grid-newRow").css('background-color','#f0f0f0');
		 }else{
        	 var span = $("#file_show");
         	 var type = files[i].fileType;
      	 	var img = typeinfo(type);
  			if(files.length<2){
  				var a="<div id='"+files[i].id+"' style='text-align: center;display: inline-block;'> "
  						+"<a><img style='width: 50px;' alt='' src='/image/"+img+"' onclick=\"show('"+files[i].id+"\',\'view\')\"></a></br>"
  						+"<a class='file-name' onclick=\"downFile('"+files[i]+"')\">" + files[i].fileName  + "</a>"
  						+"</div>";
  			}else{
  				var a="<div id='"+files[i].id+"' style='margin-left:50px;text-align: center; display: inline-block;'>"
  						+"<a><img style='width: 50px;' alt='' src='/image/"+img+"' onclick=\"show('"+files[i].id+"\',\'view\')\"></a></br>"
  						+"<a class='file-name' onclick=\"downFile('"+files[i].id+"')\">" + files[i].fileName + "</a>"
  						+"</div>";
  			}
  			span.append(a);
		  }
         }
	 }
 }
 
function typeinfo(type){
	var strImgType = "jpeg,bmp,jpg,png,tif,gif,pcx,tga,exif,fpx,svg,psd,cdr,pcd,dxf,ufo,eps,ai,raw,WMF,webp";//图片
	var img = (strImgType.search(type) != -1)?true:false;//是否是图片
	
	var zipType = "rar,zip,cab,arj,lzh,ace,tar,jar,iso,z,7-zip,bz2";
	var zip = (zipType.search(type) != -1)?true:false;//是否是压缩文件
	
	if(type=="txt"){
		return "icon-txt.png";
	}else if(type=="pdf"){
		return "icon-pdf.png";
	}else if(zip){
		return "icon-zip.png";
	}else if(img){
		return "icon-picture.png";
	}else if(type=="xlsx"||type=="xls"){
		return "icon-excel.png";
	}else if(type=="doc"||type=="docx"){
		return "icon-word.png";
	}else{
		return "icon-txt.png";
	}
}
 
//新增
function newRow() {
    var newRow = {};
    grid.addRow(newRow);
    grid.beginEditCell(newRow, "name");
    return newRow;
}
// 删除
function delRow(index,type) {
    var row = grid.getSelected();
    if (row) {
        mini.confirm("确定删除选中记录？","删除 记录",function (action) {
            if(action=='ok'){
            	
            	 if(type){
    				 if(delIds.length>0){
    					 delIds=delIds+","+index
    				 }else{
    					 delIds=delIds+index
    				 }
    				 grid.removeRow(row);
    			 }else{
    				 delete fileList[index];
    				 grid.removeRow(row);
    			 }
            	
            }
        })
    } else {
        mini.alert("请选中一条记录");
    }
}


 