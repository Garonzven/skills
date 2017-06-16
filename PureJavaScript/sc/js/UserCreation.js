Vue.use(VeeValidate);
Vue.component('v-select', VueSelect.VueSelect);

var user = new Vue({
  el: '#newUser',
  data: {
    newUser: new newUser()
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

      $.ajax({
        type: "POST",
        url: "http://localhost:8080/WebSkillsGW7/webresources/entis.people/",
        data: JSON.stringify(data),
        contentType: "application/json",
        dataType: 'json',
        success: function () {
          console.log("new skill added");
        }
      })
      /*var users = []

      $.ajax({
        url: 'http://localhost:8080/WebSkillsGW7/webresources/entis.skill/',
        type: 'GET',
        success: function (data) {
          users = data
          console.log(users)
        },
        dataType: 'json'
      })*/
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
})
