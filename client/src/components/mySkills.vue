<template>
    <div id="page-wrapper">
      <div style="display:block;" class="container" id="MySkills">
        <div class="row">
          <div class="col-md-12" style="width:100%; max-width: 900px;">
            <div style="background-color: lightgrey" class="panel panel-info">
              <div class="panel-body">
                <div class="row">
                  <div class="col-md-3 col-lg-3 " align="center">
                    <img alt="User Pic" height="180px" width="180px" id="User_Pic" src="https://javascriptair.com/resources/panelists/mzabriskie.png" class="img-rounded img-responsive">
                    <label style="margin-top: 10px" class="btn btn-default" for="file">Add Photo</label>
                  </div>
                  <div class=" col-md-9 col-lg-9 ">
                    <table class="table table-user-information table form-horizontal" style="margin-top:130px">
                      <tbody>
                        <tr>
                          <td class="form-group"><input name="name" v-model=newUser.name type="text" placeholder="NAME LASTNAME" class="form-control"></td>
                        </tr>
                        <tr>
                          <td class="form-group"><input name="job_title" v-model="newUser.jobtitle" type="text" placeholder="JOB TITLE" class="form-control">
                          </td>
                        </tr>
                        <tr>
                          <td class="form-group"><input name="location" v-model="newUser.location" type="text" placeholder="LOCATION" class="form-control">
                          </td>
                        </tr>
                        <tr>
                          <td class="form-group"><input name="email" v-model="newUser.email" type="text" placeholder="EMAIL" class="form-control">
                          </td>
                        </tr>
                        <tr>
                          <td class="form-group"><input name="phone" v-model="newUser.phone" type="text" placeholder="PHONE" class="form-control">
                          </td>
                        </tr>
                        <tr>
                          <td class="form-group">
                            <v-select :options="['Super User','Admin','Watcher','User']" v-model="newUser.idrol.name" placeholder="ROLE"></v-select>
                          </td>
                        </tr>
                        <tr>
                          <td class="form-group"><input name="rol" v-model="newUser.skillandlevel" type="text" placeholder="SKILL & LEVEL" class="form-control">
                          </td>
                        </tr>
                        <tr>
                          <td class="form-group"><input name="rol" v-model="newUser.indate" type="text" placeholder="SINCE DATE" class="form-control">
                          </td>
                        </tr>
                        <tr>
                          <td class="form-group">
                            <div id="iSearch">
                              <v-select :options="skillList" :debounce="250" multiple :value.sync="selected" v-model="newUser.skills" placeholder="SKILLS"></v-select>
                            </div>
                          </td>
                        </tr>
                        <tr>
                         <td class="form-group">
                            <p class="control has-icon has-icon-right">
                              <input name="file" id="file" @change="onFileChange" v-validate.reject="'mimes:image/*'" :class="{ 'is-danger': errors.has('file') }" type="file" style="display:none"> <i v-show="errors.has('file')" class="fa fa-warning"></i> <span v-show="errors.has('file')" class="help-block alert alert-danger">{{ errors.first('file') }}</span></p>
                          </td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
            <a class="btn btn-primary submit_buttom">Save</a>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
  const newUser = function() {
    this.name = '', this.lastname = '', this.email = '', this.phone = '',
      this.location = '', this.jobtitle = '', this.fotolink = '', this.foto = '', this.idrol = {
        idrol: '',
        name: ''
      }, this.skillandlevel = '', this.indate = '', this.pasword, this.skills = []
  }
  const localHost = 'http://localhost:8080/'
  const skillList = []
  $.get(localHost + 'WebSkillsGW7/webresources/entis.skill/', function(data) {
    for (let i = 0; i < data.length; i++) {
      skillList.push(data[i].name)
    }
    console.log('Data Loaded: ', skillList)
  })

  export default {
    data() {
      return {
        selected: [],
        newUser: new newUser(),
        skillList: skillList
      }
    },
    methods: {
      onFileChange(e) {
        var files = e.target.files || e.dataTransfer.files
        if (!files.length) return
        this.createImage(files[0])
      },
      createImage(file) {
        var image = new Image()
        var reader = new FileReader()
        var vm = this
        reader.onload = (e) => {
          vm.newUser.fotolink = e.target.result
          var photo = document.getElementById('User_Pic').src = vm.newUser.fotolink
        }
        reader.readAsDataURL(file)
      },
    },
    mounted() {
      const iSearch = document.getElementById('iSearch')
      const current = this.newUser
      const sL = this.skillList

      /*const preview = document.getElementById("preview")
        
      if(this.newUser.fotolink) {
        preview.setAttribute('src', this.newUser.fotolink)
      }*/
      iSearch.childNodes[0].childNodes[0].childNodes[1].addEventListener('keypress', function(e) {
        //console.log(currentSkill.length)
        if (current.skills.length === 0) {
          if (13 == e.keyCode) {
            var match = iSearch.childNodes[0].childNodes[0].childNodes[1].value
            for (let i = 0; i < sL.length; i++) {
              if (match != sL[i]) {
                if (match) {
                  var newSkill = {}
                  sL.push(match)
                  i = sL.length
                  var data = {}
                  data.name = match
                  console.log(data)
                  /*$.ajax({
                    type: "POST",
                    url: "http://localhost:8080/WebSkillsGW7/webresources/entis.skillpeople/",
                    data: JSON.stringify(data),
                    contentType: "application/json; odata=verbose",
                    dataType: 'json',
                    success: function () {
                      console.log("new skill added");
                    }
                  });*/
                  //currentSkill.value = []
                }
              }
            }
          }
        }
      })
    }
  }

</script>

<style>
  .submit_buttom {
    width: 100%;
    background-color: #f16521;
    text-transform: uppercase;
    border-color: #f16521;
  }
  
  .submit_buttom:hover {
    background-color: #f07020;
    border-color: #f07020;
  }
  
  .user-row {
    margin-bottom: 14px;
  }
  
  .user-row:last-child {
    margin-bottom: 0;
  }
  
  .dropdown-user {
    margin: 13px 0;
    padding: 5px;
    height: 100%;
  }
  
  .dropdown-user:hover {
    cursor: pointer;
  }
  
  .table-user-information>tbody>tr {
    border-top: 1px solid rgb(221, 221, 221);
  }
  
  .table-user-information>tbody>tr:first-child {
    border-top: 0;
  }
  
  .table-user-information>tbody>tr>td {
    border-top: 0;
  }
  
  .toppad {
    margin-top: 20px;
  }

</style>
