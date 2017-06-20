<template>
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">
            <div style="background-color: lightgrey" class="panel panel-info">
              <div class="panel-body">
                <div class="row">
                  <div class="col-md-3 col-lg-3 " align="center"> <img alt="User Pic" id="User_Pic" src="https://javascriptair.com/resources/panelists/mzabriskie.png" class="img-rounded	 img-responsive">
                    <label style="margin-top: 10px" class="btn btn-default" for="file">Add Photo</label>
                  </div>
                  <div class=" col-md-9 col-lg-9 ">
                    <table class="table table-user-information table form-horizontal" style="margin-top:130px">
                      <tbody>
                        <tr>
                          <td class="form-group"><input name="name" v-model="newUser.name" type="text" placeholder="NAME LASTNAME" class="form-control"></td>
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
            <a class="btn btn-primary submit_buttom" @click='post'>Save</a>
          </div>
        </div>

</template>

<script>
  const newUser = function() {
    this.name = '', this.lastname = '', this.email = '', this.phone = "",
      this.location = "", this.jobtitle = "", this.fotolink = "", this.foto = "", this.idrol = {
        idrol: "",
        name: ""
      }, this.skillandlevel = "", this.indate = "", this.pasword = []
  }

  const skillList = [];

  export default {
    data() {
      return {
        selected: [],
        newUser: new newUser(),
        skillList: [],
        apiUrl: 'https://13.92.199.15:45734/WebSkillsGW7/webresources'
      }
    },
    methods: {
      post() {
        let data = this.newUser;
        data.pasword = Math.random().toString(36).substr(2, 6)
        if (data.idrol.name == 'Super User') {
          data.idrol.idrol = 1
        } else if (data.idrol.name == 'Admin') {
          data.idrol.idrol = 2
        } else if (data.idrol.name == 'Watcher') {
          data.idrol.idrol = 3
        } else if (data.idrol.name == 'User') {
          data.idrol.idrol = 4
        }
        console.log(data);
        console.log(JSON.stringify(data));


        this.$http.post(this.apiUrl + '/entis.people/', data)
          .then(response => {
            console.log(response)
            //this.$auth.setToken(response.body.access_token + Date.now())

            this.$router.push('/listUser')
        })
      },
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
          var photo = document.getElementById("User_Pic").src = vm.newUser.fotolink
        }
        reader.readAsDataURL(file)
      }
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
