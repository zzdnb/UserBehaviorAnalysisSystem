// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.

 
/* eslint-disable no-new */
var vue = new Vue({
  el: '#app',
  data(){
      return{
        text:"基于Hadoop的文件上传系统",
        FileList: null,
        dirName: null,
        file: '',
        mkdirDone: false,
        nowPathList: ["/"],
        nowPath : ''          
      }
  },
  mounted () {
    axios.get('http://localhost:8080/hdfs/listFile?folder=/opt/moudle/hadoop/input/')
         .then(response => (this.FileList = response.data))
  },
  methods: {
    remove: function(e){
      var nowPath = this.nowPath==''?'/':this.nowPath
      var path = '/opt/moudle/hadoop/input/' + e.srcElement.dataset.name
      var that = this
      axios.post('http://localhost:8080/hdfs/removeFile',"path=" + path )
      .then(function(res){
          alert("删除文件/文件夹成功")
          axios.get('http://localhost:8080/hdfs/listFile?folder='+nowPath)
         .then(response => (that.FileList = response.data))
      })
    },
    download: function(e){
      var nowPath = this.nowPath==''?'/':this.nowPath
      var path = 'D:/work/UserBehaviorAnalysisSystemData/' + e.srcElement.dataset.name
      var dst = '/opt/moudle/hadoop/input/' + e.srcElement.dataset.name
      axios.post('http://localhost:8080/hdfs/downloadFile',"srcString=" + path + "&dstString=" + dst)
      .then(function(res){
        alert("文件/文件夹下载成功"+"\n"+"opt/moudle/hadoop/input/")
      })
    },
    intoDir: function(e){
      this.nowPathList.push(e.srcElement.dataset.name)
      var nowPath = '/opt/moudle/hadoop/input/'
      for(var i=1;i<this.nowPathList.length;i++){
        nowPath = nowPath + this.nowPathList[i] + '/'
      }
      this.nowPath = nowPath
      axios.get('http://localhost:8080/hdfs/listFile?folder='+nowPath)
         .then(response => (this.FileList = response.data))
    },
    backDir: function(e){
      this.nowPathList.splice(-1,1)
      var nowPath = '/'
      for(var i=1;i<this.nowPathList.length;i++){
        nowPath = nowPath + this.nowPathList[i] + '/'
      }
      axios.get('http://localhost:8080/hdfs/listFile?folder='+nowPath)
         .then(response => (this.FileList = response.data))
      if(nowPath=='/'){
        this.nowPath = ''
      }else{
        this.nowPath = nowPath
      }
      
    },
    getFile(event) {
      this.file = event.target.files[0]
      console.log(this.file)
    },
    submitForm(event) {
      event.preventDefault();
      nowPath = 'D:/work/UserBehaviorAnalysisSystemData/'+this.file.name;
      let formData = new FormData();
      formData.append('file', this.file);
      formData.append('dstString','D:/work/UserBehaviorAnalysisSystemData/'+this.file.name)

      let config = {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }
      alert(nowPath)
      axios.post('http://localhost:8080/hdfs/uploadFile', formData, config)
      .then(function (res) {
        alert("上传文件成功,请刷新查看")
        axios.get('http://localhost:8080/hdfs/listFile?folder=/opt/moudle/hadoop/input/')
         .then(response => (this.FileList = response.data))
      })
    }

  }
})

