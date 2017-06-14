$('document').ready(function() {
  /* validation */
  $("#login-form").validate({
    rules: {
      pasword: {
        required: true,
      },
      email: {
        required: true,
        email: true
      },
    },
    messages: {
      password: {
        required: "please enter your password"
      },
      email: "please enter your email address",
    },
    submitHandler: submitForm
  });
  /* validation */

  /* login submit */
  function submitForm() {
    var data = $("#login-form").serialize();
    data = JSON.stringify(data);
    console.log(data);

    $.ajax({

      type: 'POST',
      url: 'http://localhost:8080/WebSkillsGW7/webresources/entis.people/login',
      data: data,
      contentType:'application/json',
			dataType: 'json',
      beforeSend: function (xhr) {   //Include the bearer token in header
        xhr.setRequestHeader("Authorization", 'Bearer '+ jwt);
      }
    }).done(function (response) {
      console.log('ok');
    }).fail(function (err)  {
      console.log('muerete que chao');
    });

    return false;
  }
  /* login submit */
});
