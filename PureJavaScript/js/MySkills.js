$.get("http://localhost:8080/WebSkillsGW7/webresources/entis.skill/", function (data) {
  for (let i = 0; i < data.length; i++) {
    skillList.push(data[i].name)
  }
  console.log("Data Loaded: ", skillList)
})

/*$('#ex1').slider({
	formatter: function(value) {
		return 'Current value: ' + value;
	}
});*/


const newUser = function (name, lastname, email, idrol) {
  this.name = name, this.lastname = lastname, this.email = email, this.phone = idrol,
    this.location = "", this.jobtitle = "", this.fotolink = "", this.foto = "", this.idrol = {
      idrol: "",
      name: ""
    }, this.skillandlevel = "", this.indate = "", this.pasword, this.skills = []
}

let fakeJson = new newUser('Douglas', 'Mendez', 'Thisisatest3@gmail.com', {idrol:1, name:'Super User'});

Vue.use(VeeValidate)
Vue.component('v-select', VueSelect.VueSelect)

var UserData = new Vue({
  el: '#newUser',
  data: {
    selected: [],
    newUser: new newUser(),
    skillList: skillList
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

      /*$.ajax({
        type: "POST",
        url: "http://localhost:8080/WebSkillsGW7/webresources/entis.people/",
        data: JSON.stringify(data),
        contentType: "application/json",
        dataType: 'json',
        success: function () {
          console.log("new skill added");
        }*/
      var users = []

      $.ajax({
        url: 'http://localhost:8080/WebSkillsGW7/webresources/entis.skill/',
        type: 'GET',
        success: function (data) {
          users = data
          console.log(users)
        },
        dataType: 'json'
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
    },
  },
  mounted() {
    const iSearch = document.getElementById("iSearch")
    const current = this.newUser
    const sL = this.skillList

    /*const preview = document.getElementById("preview")
      
    if(this.newUser.fotolink) {
      preview.setAttribute('src', this.newUser.fotolink)
    }*/
    iSearch.childNodes[0].childNodes[0].childNodes[1].addEventListener('keypress', function (e) {
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
                $.ajax({
                  type: "POST",
                  url: "http://localhost:8080/WebSkillsGW7/webresources/entis.skillpeople/",
                  data: JSON.stringify(data),
                  contentType: "application/json; odata=verbose",
                  dataType: 'json',
                  success: function () {
                    console.log("new skill added");
                  }
                });
                //currentSkill.value = []
              }
            }
          }
        }
      }
    })
  }
})
