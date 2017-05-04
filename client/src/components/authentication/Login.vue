<template lang="html">
<div class="">
  <video class="bgvideo" ref="videoRef" src="" id="video-container" width="100%" playsinline autoplay muted loop>
  </video>

  <div class="container" style="margin-top: 85px;" id="polina">
	  <div class="row vertical-offset-100">
		  <div class="">
        <div class="panel-heading">
            <div class="row-fluid people-row">
                <img src="/static/img/LOGO-01.png" class="img-responsive" alt="Console Admin" style="text-align: center; margin-left: 10px;"/>
            </div>
        </div>      
        <form @submit.prevent="login" class="columns column is-multiline is-12 col-md-6 col-md-offset-3" role="form" style="display: block;">
          <div class="form-group" style="text-align:center;">
            <p class="control has-icon has-icon-right">
              <input input name="email" v-validate="'required|email'" :class="{'input': true, 'is-danger': errors.has('email') }" type="text" class="form-control " style="max-width: 600px;" placeholder="Email" v-model="people.email">
              <i v-show="errors.has('email')" class="fa fa-warning"></i>
              <span v-show="errors.has('email')" class="help-block alert alert-danger">{{ errors.first('email') }}</span>
            </p>
          </div>
          <div class="form-group" style="text-align:center;">
            <p class="control has-icon has-icon-right">
              <input name="pasword" v-validate="'required|min:6'" :class="{'input': true, 'is-danger': errors.has('pasword') }" type="password" class="form-control" placeholder="Password" v-model="people.pasword">
              <i v-show="errors.has('pasword')" class="fa fa-warning"></i>
              <span v-show="errors.has('pasword')" class="help-block alert alert-danger">{{ errors.first('pasword') }}</span>
            </p>
          </div>
          <div class="form-group">
            <div class="row">
              <div class="col-md-12">
                <input type="submit" tabindex="4" class="form-control btn btn-login" value="Log In" style="padding: 6px 12px;">
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="row">
              <div class="col-lg-12">
                <div class="text-center">
                  <a href="#" tabindex="5" class="forgot-password">Forgot Password?</a>
                </div>
              </div>
            </div>
          </div>
        </form>
		  </div>
	  </div>
  </div>

</div>
</template>

<script>
    export default {

        data() {
            return {
                people: {
                    email: '',
                    pasword: ''
                },
                apiUrl: 'http://localhost:8080/WebSkillsGW7/webresources'
            }
        },
        mounted: function() {
            this.$refs.videoRef.src = "static/video/VORTEX.mp4";
            this.$refs.videoRef.play();
        },
        name: 'scopes-example',
        methods: {
            login() {
                var data = JSON.stringify(this.people);
                //data = JSON.parse(data);
                this.$validator.validateAll().then(() => {
                    // eslint-disable-next-line
                    console.log(data);
                    this.$http.post(this.apiUrl + '/entis.people/login', data).then((response) => {
                        console.log(response);
                    });
                });
            }
        }
    }

</script>

<style lang="css">
    body {
        padding-top: 90px;
    }
    
    .bgvideo {
        position: fixed;
        top: 50%;
        left: 50%;
        min-width: 100%;
        min-height: 100%;
        width: auto;
        height: auto;
        z-index: -100;
        transform: translateX(-50%) translateY(-50%);
        background-size: cover;
        transition: 1s opacity;
    }
    
    #polina {
        font-weight: 100;
        background: rgba(0, 0, 0, 0.3);
        color: white;
        padding: 2rem;
        width: 33%;
        margin: auto;
        height: 50%;
        font-size: 60px;
        border-radius: 90;
        border: 0.5px solid #444444;
    }
    
    .boxx {
        margin-top: 200px;
        max-width: 900px;
    }
    
    .panel-login {
        border-color: #ccc;
        -webkit-box-shadow: 0px 2px 3px 0px rgba(0, 0, 0, 0.2);
        -moz-box-shadow: 0px 2px 3px 0px rgba(0, 0, 0, 0.2);
        box-shadow: 0px 2px 3px 0px rgba(0, 0, 0, 0.2);
    }
    
    .panel-login>.panel-heading {
        color: #00415d;
        background-color: #fff;
        border-color: #fff;
        text-align: center;
    }
    
    .panel-login>.panel-heading a {
        text-decoration: none;
        color: #666;
        font-weight: bold;
        font-size: 15px;
        -webkit-transition: all 0.1s linear;
        -moz-transition: all 0.1s linear;
        transition: all 0.1s linear;
    }
    
    .panel-login>.panel-heading a.active {
        color: #029f5b;
        font-size: 18px;
    }
    
    .panel-login>.panel-heading hr {
        margin-top: 10px;
        margin-bottom: 0px;
        clear: both;
        border: 0;
        height: 1px;
        background-image: -webkit-linear-gradient(left, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.15), rgba(0, 0, 0, 0));
        background-image: -moz-linear-gradient(left, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.15), rgba(0, 0, 0, 0));
        background-image: -ms-linear-gradient(left, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.15), rgba(0, 0, 0, 0));
        background-image: -o-linear-gradient(left, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.15), rgba(0, 0, 0, 0));
    }
    
    .panel-login input[type="text"],
    .panel-login input[type="email"],
    .panel-login input[type="password"] {
        height: 45px;
        border: 1px solid #ddd;
        font-size: 16px;
        -webkit-transition: all 0.1s linear;
        -moz-transition: all 0.1s linear;
        transition: all 0.1s linear;
    }
    
    .panel-login input:hover,
    .panel-login input:focus {
        outline: none;
        -webkit-box-shadow: none;
        -moz-box-shadow: none;
        box-shadow: none;
        border-color: #ccc;
    }
    
    .btn-login {
        background-color: #f16521;
        outline: none;
        color: #fff;
        font-size: 14px;
        height: auto;
        font-weight: normal;
        padding: 14px 0;
        text-transform: uppercase;
        border-color: #f16521;
    }
    
    .btn-login:hover,
    .btn-login:focus {
        color: #fff;
        background-color: #f07020;
        border-color: #ffb366;
    }
    
    .forgot-password {
        text-decoration: underline;
        color: #888;
    }
    
    .forgot-password:hover,
    .forgot-password:focus {
        text-decoration: underline;
        color: #666;
    }
    
    .btn-register {
        background-color: #1CB94E;
        outline: none;
        color: #fff;
        font-size: 14px;
        height: auto;
        font-weight: normal;
        padding: 14px 0;
        text-transform: uppercase;
        border-color: #1CB94A;
    }
    
    .btn-register:hover,
    .btn-register:focus {
        color: #fff;
        background-color: #1CA347;
        border-color: #1CA347;
    }

</style>
