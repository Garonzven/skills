<template>
  <div class="bg">
    <div class="container" id="polina">
      <div class="row vertical-offset-100">
        <div class="">
          <div class="panel-heading">
            <div class="row-fluid people-row">
              <h4 class="img-responsive" alt="Conxole Admin" style="text-align: center;">CHANGE PASSWORD</h4>
            </div>
          </div>
          <form @submit.prevent="login" class="columns column is-multiline is-12 col-md-6 col-md-offset-3" role="form" style="display: block;">
            <div class="form-group" style="text-align:center;">
              <p class="control has-icon has-icon-right">
                <input name="password" v-validate="'required|min:6'" :class="{'input': true, 'is-danger': errors.has('password') }" type="password" class="form-control" placeholder="Password" >
                <i v-show="errors.has('password')" class="fa fa-warning"></i>
                <span v-show="errors.has('password')" class="help-block alert alert-danger">{{ errors.first('password') }}</span>
              </p>
            </div>
            <div class="form-group" style="text-align:center;">
              <p class="control has-icon has-icon-right">
                <input name="password" v-validate="'required|min:6'" :class="{'input': true, 'is-danger': errors.has('password') }" type="password" class="form-control" placeholder="New Password" >
                <i v-show="errors.has('password')" class="fa fa-warning"></i>
                <span v-show="errors.has('password')" class="help-block alert alert-danger">{{ errors.first('password') }}</span>
              </p>
            </div>
            <div class="form-group">
              <div class="row">
                <div class="col-md-12">
                  <input type="submit" tabindex="4" class="form-control btn btn-login" value="Log In" style="padding: 6px 12px;">
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
}
</script>

<style>
#polina {

  font-weight:100;
  background: rgba(0,0,0,0.3);
  color: white;
  padding: 2rem;
  width: 33%;
  margin: auto;
  height: 50%;
  font-size: 16px;
  border-radius: 4px;
  border: 0.5px solid #444444;
}

.panel-login {
	border-color: #ccc;
	-webkit-box-shadow: 0px 2px 3px 0px rgba(0,0,0,0.2);
	-moz-box-shadow: 0px 2px 3px 0px rgba(0,0,0,0.2);
	box-shadow: 0px 2px 3px 0px rgba(0,0,0,0.2);
}
.panel-login>.panel-heading {
	color: #00415d;
	background-color: #fff;
	border-color: #fff;
	text-align:center;
}
.panel-login>.panel-heading a{
	text-decoration: none;
	color: #666;
	font-weight: bold;
	font-size: 15px;
	-webkit-transition: all 0.1s linear;
	-moz-transition: all 0.1s linear;
	transition: all 0.1s linear;
}
.panel-login>.panel-heading a.active{
	color: #029f5b;
	font-size: 18px;
}
.panel-login>.panel-heading hr{
	margin-top: 10px;
	margin-bottom: 0px;
	clear: both;
	border: 0;
	height: 1px;
	background-image: -webkit-linear-gradient(left,rgba(0, 0, 0, 0),rgba(0, 0, 0, 0.15),rgba(0, 0, 0, 0));
	background-image: -moz-linear-gradient(left,rgba(0,0,0,0),rgba(0,0,0,0.15),rgba(0,0,0,0));
	background-image: -ms-linear-gradient(left,rgba(0,0,0,0),rgba(0,0,0,0.15),rgba(0,0,0,0));
	background-image: -o-linear-gradient(left,rgba(0,0,0,0),rgba(0,0,0,0.15),rgba(0,0,0,0));
}
.panel-login input[type="text"],.panel-login input[type="email"],.panel-login input[type="password"] {
	height: 45px;
	border: 1px solid #ddd;
	font-size: 16px;
	-webkit-transition: all 0.1s linear;
	-moz-transition: all 0.1s linear;
	transition: all 0.1s linear;
}
.panel-login input:hover,
.panel-login input:focus {
	outline:none;
	-webkit-box-shadow: none;
	-moz-box-shadow: none;
	box-shadow: none;
	border-color: #ccc;
}
.btn-login {
	background-color: black;
	outline: none;
	color: #fff;
	font-size: 14px;
	height: auto;
	font-weight: normal;
	padding: 14px 0;
	text-transform: uppercase;
	border-color: #fff;
}
.btn-login:hover,
.btn-login:focus {
	color: #fff;
	background-color: black;
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
</style>
