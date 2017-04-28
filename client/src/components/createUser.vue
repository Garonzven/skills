<template lang="html">
	<div class="container-fluid">
	<div class="row">
		<div class="col-md-3"> <img alt="Bootstrap Image Preview" src="getImgUrl(logo)" class="img-rounded"/></div>
		<div class="col-md-9 form-container">
			<form @submit.prevent="validateBeforeSubmit">
				<div class="column is-12 form-group">
					<p class="control has-icon has-icon-right">
						<input name="Name" v-model="newUser.names" v-validate:name.initial="'required|alpha'" :class="{'input': true, 'is-danger': errors.has('Name') }" type="text" placeholder="Name" class="input-style"> <i v-show="errors.has('Name')" class="fa fa-warning"></i> <span v-show="errors.has('Name')" class="help-block alert alert-danger">{{ errors.first('Name') }}</span> </p>
				</div>
				<div class="column is-12 form-group">
					<p class="control has-icon has-icon-right">
						<input name="last_name" v-model="newUser.last_names" v-validate:name.initial="'required|alpha'" :class="{'input': true, 'is-danger': errors.has('last_name') }" type="text" placeholder="Last Name" class="input-style"> <i v-show="errors.has('last_name')" class="fa fa-warning"></i> <span v-show="errors.has('last_name')" class="help-block alert alert-danger">{{ errors.first('last_name') }}</span> </p>
				</div>
				<div class="column is-12 form-group">
					<p class="control has-icon has-icon-right">
						<input name="job_title" v-model="newUser.job_title" v-validate:name.initial="'required|alpha'" :class="{'input': true, 'is-danger': errors.has('job_title') }" type="text" placeholder="Job Title" class="input-style"> <i v-show="errors.has('job_title')" class="fa fa-warning"></i> <span v-show="errors.has('job_title')" class="help-block alert alert-danger">{{ errors.first('job_title') }}</span> </p>
				</div>
				<!--div class="column is-12 form-group">
					<p class="control has-icon has-icon-right">
						<input name="nationality" v-model="newUser.nationality" v-validate:name.initial="'required|alpha'" :class="{'input': true, 'is-danger': errors.has('nationality') }" type="text" placeholder="Nationality"> <i v-show="errors.has('nationality')" class="fa fa-warning"></i> <span v-show="errors.has('nationality')" class="help-block alert alert-danger">{{ errors.first('nationality') }}</span> </p>
				</div-->
				<div class="column is-12 form-group" style="text-align: center">
					<v-select :options="['Ven','Us','Eu']" style="width: 300px"></v-select>
				</div>
				<div class="column is-12 form-group">
					<p class="control has-icon has-icon-right">
						<input name="email" v-model="newUser.email" v-validate:email.initial="'required|email'" :class="{'input': true, 'is-danger': errors.has('email') }" type="text" placeholder="Email" class="input-style"> <i v-show="errors.has('email')" class="fa fa-warning"></i> <span v-show="errors.has('email')" class="help-block alert alert-danger">{{ errors.first('email') }}</span> </p>
				</div>
				<div class="column is-12 form-group">
					<p class="control has-icon has-icon-right">
						<input name="phone" v-model="newUser.phone" v-validate:phone.initial="'required|numeric'" :class="{'input': true, 'is-danger': errors.has('phone') }" type="text" placeholder="Phone" class="input-style"> <i v-show="errors.has('phone')" class="fa fa-warning"></i> <span v-show="errors.has('phone')" class="help-block alert alert-danger">{{ errors.first('phone') }}</span> </p>
				</div>
				<div class="column is-12 form-group">
					<p class="control has-icon has-icon-right">
						<input name="identification" v-model="newUser.identification" v-validate:phone.initial="'required|numeric'" :class="{'input': true, 'is-danger': errors.has('identification') }" type="text" placeholder="Identification" class="input-style"> <i v-show="errors.has('phone')" class="fa fa-warning"></i> <span v-show="errors.has('identification')" class="help-block alert alert-danger">{{ errors.first('identification') }}</span> </p>
				</div>
				<div class="column is-12">
					<label class="btn btn-default" for="file">Photo</label>
					<p class="control has-icon has-icon-right">
						<input name="file" id="file" @click="onFileChange" v-validate.reject="'mimes:image/*'" :class="{ 'is-danger': errors.has('file') }" type="file" style="display:none"> <i v-show="errors.has('file')" class="fa fa-warning"></i> <span v-show="errors.has('file')" class="help-block alert alert-danger">{{ errors.first('file') }}</span> </p>
				</div>
				<div class="column is-12">
					<p class="control">
						<button class="button is-primary" type="submit">Submit</button>
					</p>
				</div>
			</form> <pre>{{$data.newUser}}</pre> </div>
	</div>
</div>
</template>

<script >	
export default {
	name: 'createUser'
	, data() {
		return {
			newUser: {
				names: ""
				, last_names: ""
				, email: ""
				, nationality: ""
				, identification: ""
				, skills: []
				, phone: ""
				, photo: ""
				, job_title: ""
			, }
			, //selected:'',
			//data: new userForm(),
			show: false
			, regUrl: "http://192.168.2.128:8080/vueLogin"
		};
	}
	, methods: {
		onFileChange(e) {
				var files = e.target.files || e.dataTransfer.files;
				if (!files.length) return;
				this.createImage(files[0]);
			}
			, createImage(file) {
				var image = new Image();
				var reader = new FileReader();
				var vm = this;
				reader.onload = (e) => {
					vm.newUser.photo = e.target.result;
				};
				reader.readAsDataURL(file);
			},
			validateBeforeSubmit() {
            this.$validator.validateAll().then(() => {
                // eslint-disable-next-line
				let user = JSON.stringify(this.newUser);
				dataBank.push(JSON.parse(user));
				this.resetState();
                alert('Form Submitted!');
            }).catch(() => {
                // eslint-disable-next-line
                alert('Correct them errors!');
            });
        }
			, resetState: function () {
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
			}
		,getImgUrl(pet) {
    var images = require.context('../assets', false, /\.png$/)
    return images('./' + pet + ".png")
  }
	}
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
    .form-container{
        max-width: 600px;
    }

.input-style{
width: 350px;
height: 50px;
/*-webkit-border-radius: 40px;
-moz-border-radius: 40px;
border-radius: 40px;*/
}

</style>