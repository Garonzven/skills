const skillList = []

const newUser = function (name, lastname, email) {
  this.name = name, this.lastname = lastname, this.email = email, this.phone = "",
    this.location = "", this.jobtitle = "", this.fotolink = "", this.foto = "", this.idrol = {
      idrol: "",
      name: ""
    }, this.skillandlevel = "", this.indate = "", this.pasword, this.skills = []
}

function reply_click(clicked_id) {
  console.log(clicked_id);
  if (clicked_id === "MyProfile") {
    document.getElementById("Skills").style.display = 'none'
    document.getElementById("MySkills").style.display = 'block'
    document.getElementById("app").style.display = 'none'
  }
  else if (clicked_id === "User") {
    document.getElementById("Skills").style.display = 'none'
    document.getElementById("MySkills").style.display = 'none'
    document.getElementById("app").style.display = 'block'
  }
  else if (clicked_id === "SkillsList") {
    document.getElementById("Skills").style.display = 'block'
    document.getElementById("MySkills").style.display = 'none'
    document.getElementById("app").style.display = 'none'
  }
}
