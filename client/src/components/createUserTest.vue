<template lang="html">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2"> <img id="preview" alt="Bootstrap Image Preview" height="150" width="150" src="static/img/logo.png" class="img-rounded" />
				<label class="btn btn-default" for="file">Photo</label>
			</div>
			<div class="col-md-10 form-container">
				<canvas height="150" width="100"></canvas>
				<form>
					<div class="column is-12 form-group">
						<p class="control has-icon has-icon-right">
							<input name="name" v-model="newUser.name" v-validate:name.initial="'required|alpha'" :class="{'input': true, 'is-danger': errors.has('name') }" type="text" placeholder="Name" class="input-style form-control"> <i v-show="errors.has('name')" class="fa fa-warning"></i> <span v-show="errors.has('name')" class="help-block alert alert-danger">{{ errors.first('name') }}</span> </p>
					</div>
					<div class="column is-12 form-group">
						<p class="control has-icon has-icon-right">
							<input name="lastname" v-model="newUser.lastname" v-validate:lastname.initial="'required|alpha'" :class="{'input': true, 'is-danger': errors.has('lastname') }" type="text" placeholder="Last Name" class="input-style form-control"> <i v-show="errors.has('lastname')" class="fa fa-warning"></i> <span v-show="errors.has('lastname')" class="help-block alert alert-danger">{{ errors.first('lastname') }}</span> </p>
					</div>
					<div class="column is-12 form-group">
						<p class="control has-icon has-icon-right">
							<input name="password" v-model="newUser.password" v-validate="'required|min:6'" :class="{'input': true, 'is-danger': errors.has('password') }" type="password" placeholder="Password" class="input-style form-control"> <i v-show="errors.has('password')" class="fa fa-warning"></i> <span v-show="errors.has('password')" class="help-block alert alert-danger">{{ errors.first('password') }}</span> </p>
					</div>
					<div class="column is-12 form-group">
						<p class="control has-icon has-icon-right">
							<input name="jobtitle" v-model="newUser.jobtitle" v-validate:jobtitle.initial="'required|alpha'" :class="{'input': true, 'is-danger': errors.has('jobtitle') }" type="text" placeholder="Job Title" class="input-style form-control"> <i v-show="errors.has('jobtitle')" class="fa fa-warning"></i> <span v-show="errors.has('jobtitle')" class="help-block alert alert-danger">{{ errors.first('jobtitle') }}</span> </p>
					</div>
					<!--div class="column is-12 form-group">
						<v-select :options="['Ven','Us','Eu']" v-model="newUser.nationality" placeholder="Nationality"></v-select>
					</div-->
					<div class="column is-12 form-group">
						<v-select :options="options" v-model="newUser.idrol" placeholder="Rol"></v-select>
					</div>
					<div class="column is-12 form-group">
						<p class="control has-icon has-icon-right">
							<input name="email" v-model="newUser.email" v-validate:email.initial="'required|email'" :class="{'input': true, 'is-danger': errors.has('email') }" type="text" placeholder="Email" class="input-style form-control"> <i v-show="errors.has('email')" class="fa fa-warning"></i> <span v-show="errors.has('email')" class="help-block alert alert-danger">{{ errors.first('email') }}</span> </p>
					</div>
					<!--div class="column is-12 form-group">
						<p class="control has-icon has-icon-right">
							<input name="phone" v-model="newUser.phone" v-validate:phone.initial="'required|numeric'" :class="{'input': true, 'is-danger': errors.has('phone') }" type="text" placeholder="Phone" class="input-style"> <i v-show="errors.has('phone')" class="fa fa-warning"></i> <span v-show="errors.has('phone')" class="help-block alert alert-danger">{{ errors.first('phone') }}</span> </p>
					</div-->
					<!--div class="column is-12 form-group">
						<p class="control has-icon has-icon-right">
							<input name="identification" v-model="newUser.identification" v-validate:identification.initial="'required|numeric'" :class="{'input': true, 'is-danger': errors.has('identification') }" type="text" placeholder="Identification" class="input-style"> <i v-show="errors.has('phone')" class="fa fa-warning"></i> <span v-show="errors.has('identification')" class="help-block alert alert-danger">{{ errors.first('identification') }}</span> </p>
					</div-->
					<!--div class="column is-12 form-group" id="iSearch">
						<v-select :options="skillList" :on-change="addSkill" multiple :value.sync="selected" v-model="newUser.skills" placeholder="Skills"></v-select>
					</div-->
					<!--div class="column is-12">
						<ul class="list-group" id="skill-range">
							<li class="list-group-item clearfix" v-for="(skill, index) in skill_sliders">
								<div class="skill-title"> {{skill.name}}</div>
								<div class="level-range"><vue-slider ref="slider" v-bind="skill" v-model="skill.value"></vue-slider></div>
							</li>
						</ul>
					</div-->
					<div class="column is-12 pull-left ">
						<p class="control has-icon has-icon-right">
							<input name="file" id="file" @change="onFileChange" v-validate.reject="'mimes:image/*'" :class="{ 'is-danger': errors.has('file') }" type="file" style="display:none"> <i v-show="errors.has('file')" class="fa fa-warning"></i> <span v-show="errors.has('file')" class="help-block alert alert-danger">{{ errors.first('file') }}</span> </p>
					</div>
					<!--div class="column is-12 pull-left ">
						<vue-slider  ref="slider" v-bind="demo1" v-model="demo1.value"></vue-slider>
					</div-->
					<div class="column is-12 pull-right">
						<p class="control">
							<!--button type="submit" class="btn btn-primary">Submit</button-->
							<input type="button" @click="validateBeforeSubmit" class="btn btn-primary" value="Input Button"> </p>
					</div>
				</form> <pre>{{$data.newUser}}</pre> </div>
		</div>
	</div>
</template>

<script>
import vueSlider from 'vue-slider-component';
/*const newUser = function () {
	this.names = "", this.last_names = "", this.email = "", this.nationality = "", this.identification = "", this.skills = [], this.phone = "", this.photo = "", this.job_title = "", this.rol = ""
};*/
	
const newUser = function () {
	this.name = "", this.lastname = "", this.email = "", this.password = "", this.jobtitle = "", this.fotolink = "", this.foto = null, this.idrol = 0
};

var lazySlider = function () {
	this.value = 1;
	this.width = 280;
	this.height = 8;
	this.dotSize = 20;
	this.min = 1;
	this.max = 3;
	this.interval = 1;
	this.disabled = false;
	this.show = true;
	this.speed = 0.3;
	this.reverse = false;
	this.lazy = true;
	this.tooltip = 'hover';
	this.piecewise = true;
	this.data = ['Junior: text', 'Senior: text', 'Master: text'];
	this.processStyle = {
		"backgroundImage": "-webkit-linear-gradient(right, #f05b72, #3498db)"
	};
	this.tooltipStyle = {
		"backgroundColor": "#ff8000"
		, "borderColor": "#000000"
	};
};
	
export default {
	name: 'createUser'
	, components: {
		vueSlider
	}
	, data() {
		return {
			selected: null
			, skill_sliders: []
			, options : [{label: 'Super User', value: 0},{label: 'Admin', value: 1},{label: 'Watcher', value: 2}, {label: 'User', value: 3}]
			, skillList: ['Loren ipsum']
			, newUser: new newUser()
			, show: false
			, regUrl: "http://192.168.2.128:8080/vueLogin"
		};
	}
	, methods: {
		onFileChange(e) {
			var files = e.target.files || e.dataTransfer.files;
		if (!files.length) return;
			this.createImage(files[0]);
		}, createImage(file) {
			var image = new Image();
			var reader = new FileReader();
			var vm = this;
			reader.onload = (e) => {
				vm.newUser.fotolink = e.target.result;
				var photo = document.getElementById("preview").src = vm.newUser.fotolink;
			};
			reader.readAsDataURL(file);
		} /*validateBeforeSubmit() {
			console.log(this.$validator);
				this.$validator.validateAll().then(() => {
					// eslint-disable-next-line
					let user = JSON.stringify(this.newUser);
					user = JSON.parse(user);
					user.idrol = idrol.value;
					console.log(user)
					/*this.$http.post('http://localhost:8080/WebSkillsGW7/webresources/entis.people/', user, {
   						headers: {
       						'Content-Type': 'application/json;charset=UTF-8'
   							}
						})
					dataBank.push(user);
					alert('From Submitted!');
				}).catch(() => {
					// eslint-disable-next-line
					alert('Correct them errors!');
				});
		},*/
		
		,validateBeforeSubmit() {
            this.$validator.validateAll().then(() => {
				let user = JSON.stringify(this.newUser);
				user = JSON.parse(user);
				user.idrol = user.idrol.value;
				dataBank.push(user);
                // eslint-disable-next-line
				this.$http.post('http://localhost:8080/WebSkillsGW7/webresources/entis.people/', user, {
   						headers: {
       						'Content-Type': 'application/json;charset=UTF-8'
   							}
						})
                alert('From Submitted!');
            }).catch(() => {
                // eslint-disable-next-line
                alert('Correct them errors!');
            });
        }
		
		,resetState: function () {
			this.newUser.names = "";
			this.newUser.last_names = "";
			this.newUser.email = "";
			this.newUser.nationality = "";
			this.newUser.identification = '';
			this.newUser.address = "";
			this.newUser.skills = [];
			this.newUser.phone = '';
			this.newUser.photo = "";
			this.newUser.job_title = "";
		}, addSkill: function () {
			if (this.newUser.skills.length > 0) {
				this.skill_sliders = [];
				for (let i = 0; i < this.newUser.skills.length; i++) {
					var slider = new lazySlider()
					slider.name = this.newUser.skills[i]
					this.skill_sliders.push(slider)
				}
				console.log(this.skill_sliders);
			}else{
				this.skill_sliders = [];
			}
		}
	}
	, /*mounted() {
		var iSearch = document.getElementById("iSearch");
		
		var currentSkill = this.newUser.skills;
		var sL = this.skillList;
		iSearch.childNodes[0].childNodes[0].childNodes[1].addEventListener('keypress', function (e) {
			//console.log(currentSkill.length);
			if (currentSkill.length === 0) {
				if (13 == e.keyCode) {
					var match = iSearch.childNodes[0].childNodes[0].childNodes[1].value
					for (let i = 0; i < sL.length; i++) {
						if (match != sL[i]) {
							if (match) {
								sL.push(match);
								i = sL.length;
							}
						}
					}
				}
			}
		});
	}*/
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
    .form-container{
        max-width: 600px;
    }
.skill-title {
    float: left;
}
.level-range {
    float: right;
}


.input-style{
width: 570px;
height: 30px;
/*-webkit-border-radius: 40px;
-moz-border-radius: 40px;
border-radius: 40px;*/
}

</style>