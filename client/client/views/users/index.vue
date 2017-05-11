<template lang="html">
	<div class="container-fluid" style="background-color: lightgray;">
		<div class="row" style="padding-bottom: 10px; padding-top: 10px;">
			<div class="col-md-12"> <img id="preview" alt="Bootstrap Image Preview" height="150" width="150" src="~assets/img/BlankPic.jpg" class="img-rounded" />
			</div>
			<div class="col-md-12" style="padding-top: 10px; padding-left: 35px;">
				<label class="btn btn-default" for="file">Adjuntar Foto</label>
			</div>
			<p></p>
		</div>
		<div class="row">
			<div class="col-md-12">
				<form v-on:submit.prevent="validateBeforeSubmit">
					<div class="column is-12 form-group">
						<p class="control has-icon has-icon-right">
              <input name="name" v-model="newUser.name" v-validate:name.initial="'required|alpha'" :class="{'input': true, 'is-danger': errors.has('name') }" type="text" placeholder="Name" class="input">
							<i v-show="errors.has('name')" class="fa fa-warning"></i>
							<span v-show="errors.has('name')" class="help-block alert alert-danger">{{ errors.first('name') }}</span>
            </p>
					</div>
					<div class="column is-12 form-group">
						<p class="control has-icon has-icon-right">
							<input name="lastname" v-model="newUser.lastname" v-validate:lastname.initial="'required|alpha'" :class="{'input': true, 'is-danger': errors.has('lastname') }" type="text" placeholder="Last Name" class="input-style form-control">
							<i v-show="errors.has('lastname')" class="fa fa-warning"></i>
							<span v-show="errors.has('lastname')" class="help-block alert alert-danger">{{ errors.first('lastname') }}</span>
						</p>
					</div>
					<div class="column is-12 form-group">
						<p class="control has-icon has-icon-right">
							<input name="jobtitle" v-model="newUser.jobtitle" v-validate:jobtitle.initial="'required|alpha'" :class="{'input': true, 'is-danger': errors.has('jobtitle') }" type="text" placeholder="Job Title" class="input-style form-control">
							<i v-show="errors.has('jobtitle')" class="fa fa-warning"></i>
							<span v-show="errors.has('jobtitle')" class="help-block alert alert-danger">{{ errors.first('jobtitle') }}</span>
						</p>
					</div>
					<div class="column is-12 form-group">
						<v-select :options="options" v-model="newUser.idrol" style="min-width:30px; background-color: white;" placeholder="Rol"></v-select>
					</div>
					<div class="column is-12 form-group">
						<p class="control has-icon has-icon-right">
							<input name="email" v-model="newUser.email" v-validate:email.initial="'required|email|min:16'" :class="{'input': true, 'is-danger': errors.has('email') }" type="text" placeholder="Email" class="input-style form-control">
							<i v-show="errors.has('email')" class="fa fa-warning"></i>
							<span v-show="errors.has('email')" class="help-block alert alert-danger">{{ errors.first('email') }}</span>
						</p>
					</div>
					<div class="column is-12 pull-left ">
						<p class="control has-icon has-icon-right">
							<input name="file" id="file" @change="onFileChange" v-validate.reject="'mimes:image/*'" :class="{ 'is-danger': errors.has('file') }" type="file" style="display:none">
							<i v-show="errors.has('file')" class="fa fa-warning"></i>
							<span v-show="errors.has('file')" class="help-block alert alert-danger">{{ errors.first('file') }}</span>
						</p>
					</div>
					<div class="column is-12 pull-right">
						<p class="control">
							<!--button type="submit" class="btn btn-primary">Submit</button-->
							<a type="button" @click="validateBeforeSubmit" class="btn btn-primary" value="Submit">Save</a>
						</p>
					</div>
				</form>
				<pre>{{$store.state.session}}</pre>
			</div>
		</div>
	</div>
</template>

<script>
	import vueSlider from 'vue-slider-component'

	const newUser = function () {
		this.name = "", this.lastname = "", this.email = "", this.pasword = "", this.jobtitle = "", this.fotolink = "", this.foto = null, this.idrol = 0
	}

	var lazySlider = function() {
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
		}
		this.tooltipStyle = {
			"backgroundColor": "#ff8000",
			"borderColor": "#000000"
		}
	}

	export default {
		name: 'createUser',
		components: {
			vueSlider
		},
		data() {
			return {
				selected: null,
				skill_sliders: [],
				options: [{
					label: 'Super User',
					value: 1
				}, {
					label: 'Admin',
					value: 2
				}, {
					label: 'Watcher',
					value: 3
				}, {
					label: 'User',
					value: 4
				}],
				skillList: ['Lorem ipsum'],
				newUser: new newUser(),
				show: false,
				apiUrl: 'http://localhost:8080/WebSkillsGW7/webresources/'
			}
		},
		methods: {
			onFileChange (e) {
				var files = e.target.files || e.dataTransfer.files
				if (!files.length) return
				this.createImage(files[0])
			},
			createImage (file) {
				var image = new Image()
				var reader = new FileReader()
				var vm = this
				reader.onload = (e) => {
					vm.newUser.fotolink = e.target.result
					var photo = document.getElementById("preview").src = vm.newUser.fotolink
				}
				reader.readAsDataURL(file)
			},
			validateBeforeSubmit () {
				this.$validator.validateAll().then(() => {
					let user = JSON.stringify(this.newUser)
					user = JSON.parse(user)
					user.idrol = user.idrol.value
					user.pasword = Math.random().toString(36).substr(2, 6)
					user = JSON.stringify(user)
					console.log(user)
					/*axios.post(this.apiUrl + 'entis.people', user, {
						headers: {
							"Content-Type": "aplication/json"
						}
					}).then((response) => {
						console.log(response)
					})*/
					$.ajax({
						type: "GET",
						url: this.apiUrl + 'entis.people',
						data: user,
						contentType:"application/json; odata=verbose",
						dataType: 'json',
						success: function () {
							console.log('success')
						}
					})
					alert('From Submitted!')
				}).catch(() => {
					// eslint-disable-next-line
					console.log('Error on Submit!')
				})
			}
		}
	}

</script>

<style>
	.form-container {
		max-width: 1000px;
	}

	.skill-title {
		float: left;
	}

	.level-range {
		float: right;
	}

	.input-style {
		background-color: white;
		border: 1px solid #aba8a8;
		//height: 30px;
		/*-webkit-border-radius: 40px;
-moz-border-radius: 40px;
border-radius: 40px;*/
	}

</style>
