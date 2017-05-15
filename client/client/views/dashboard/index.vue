<template>
  <section>
    <div class="actions">
      <router-link class="btn btn-default" :to="{path: '/dashboard/createUser'}">
        <span class="glyphicon glyphicon-plus"></span>
        Add User
      </router-link>
    </div>
    <div class="column is-12 form-group" id="iSearch">
      <v-select :options="skillList" :value.sync="selected" :on-change="search" :debounce="250" multiple placeholder="Search Skills"></v-select>
    </div>
    <div class="no-padding lib-item" data-category="view">
      <div class="lib-panel col-lg-6" v-for="user in users">
        <div class="row box-shadow">
          <div class="col-md-4">
            <img class="lib-img-show" src="http://lorempixel.com/850/850/?random=123">
          </div>
          <div class="col-md-6">
            <div class="lib-row lib-header">
              {{ user.name + " " + user.lastname}}
              <div class="lib-header-seperator"></div>
            </div>
            <div class="lib-row lib-desc">
              <p>
                {{ user.email }}
              </p>
            </div>

          </div>
          <a class="col-md">
            <i class="fa fa-pencil" value="edit"></i>
          </a>
          <a class="col-md" href="#">
            <i class="icon-trash"></i>
          </a>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
  import axios from 'axios'

  const skillList = []

  axios.get('http://localhost:8080/WebSkillsGW7/webresources/entis.skill/')
    .then(response => {
      // JSON responses are automatically parsed.
      for (let i = 0; i < response.data.length; i++) {
        skillList.push(response.data[i].name)
      }
      console.log('Data Loaded: ', skillList)
    })
  /* var users = []

      $.ajax({
        url: 'http://localhost:8080/WebSkillsGW7/webresources/entis.people/',
        type: 'GET',
        success: function (data) {
          users = data
          console.log(users)
        },
        dataType: 'json'
      }) */
  export default {
    data: () => ({
      selected: [],
      users: [],
      skillList: skillList
    }),

    // Fetches posts when the component is created.
    created () {
      axios.get(`http://localhost:8080/WebSkillsGW7/webresources/entis.people/`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.users = response.data
        })
      /* .catch(e => {
        this.errors.push(e)
      }) */

      // async / await version (created() becomes async created())
      //
      // try {
      //   const response = await axios.get(`http://jsonplaceholder.typicode.com/posts`)
      //   this.posts = response.data
      // } catch (e) {
      //   this.errors.push(e)
      // }
    },
    methods: {
      search () {
        console.log('search for:', this.selected)
        if (this.selected.length === 0) {
          axios.get(`http://localhost:8080/WebSkillsGW7/webresources/entis.people/`)
            .then(response => {
              // JSON responses are automatically parsed.
              this.users = response.data
            })
        } else {
          console.log('search for:', this.users)
          this.users = []
        }
      }
    }
  }

</script>

<style>
  .lib-panel {
    margin-bottom: 20Px;
    padding: 20px
  }
  
  .lib-panel img {
    width: 100%;
    background-color: transparent;
  }
  
  .lib-panel .row,
  .lib-panel .col-md-6 {
    padding: 0;
    background-color: #FFFFFF;
  }
  
  .lib-panel .lib-row {
    padding: 0 20px 0 20px;
  }
  
  .lib-panel .lib-row.lib-header {
    background-color: #FFFFFF;
    font-size: 20px;
    padding: 10px 20px 0 20px;
  }
  
  .lib-panel .lib-row.lib-header .lib-header-seperator {
    height: 2px;
    width: 26px;
    background-color: #d9d9d9;
    margin: 7px 0 7px 0;
  }
  
  .lib-panel .lib-row.lib-desc {
    position: relative;
    height: 100%;
    display: block;
    font-size: 13px;
  }
  
  .lib-panel .lib-row.lib-desc a {
    position: absolute;
    width: 100%;
    bottom: 10px;
    left: 20px;
  }
  
  .row-margin-bottom {
    margin-bottom: 20px;
  }
  
  .box-shadow {
    -webkit-box-shadow: 0 0 10px 0 rgba(0, 0, 0, .10);
    box-shadow: 0 0 10px 0 rgba(0, 0, 0, .10);
  }
  
  .no-padding {
    padding: 0;
  }

</style>
