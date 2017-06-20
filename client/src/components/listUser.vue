<template>
    <section>
        <div class="actions">
          <router-link to="/createUser" class="btn" id="addUser">
            <span>Add New User</span>
            <img src="../assets/plus.png" alt="">
          </router-link>
        </div>
        <div class="column is-12 form-group" id="iSearch">
          <v-select :options="skillList" :value.sync="selected" :on-change="search" :debounce="250" multiple placeholder="Search Skills"></v-select>
        </div>
        <div class="no-padding lib-item" data-category="view">
          <div class="lib-panel col-lg-6" v-for="user in users">
            <div class="row box-shadow">
              <div class="col-md-4">
                <img class="lib-img-show" src="http://placehold.it/300x200?text=N/A">

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
              <div class="col-md-2 tagy">
                <a href="">
                  <img src="../assets/edit.png" alt="">
                </a>
                <a href="#">
                  <img src="../assets/delete.png" alt="">
                </a>
              </div>
            </div>
          </div>
        </div>
      </section>
</template>

<script>
    export default {
        data() {
            return {
                selected: [],
                users: [],
                skillList: []
            }
        },
        created() {
            this.$http.get('https://13.92.199.15:45734/WebSkillsGW7/webresources/entis.people/')
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
            search() {
                console.log('search for:', this.selected)
                if (this.selected.length === 0) {
                    this.$http.get('https://13.92.199.15:45734/WebSkillsGW7/webresources/entis.people/')
                        .then(response => {
                            // JSON responses are automatically parsed.
                            this.users = response.data
                        })
                } else {
                    console.log('search for:', this.users)
                    this.users = []
                }
            },
        }
    }

</script>
<style>
    .lib-panel {
        margin-bottom: 20Px;
        padding: 20px;
    }

    .lib-panel img {
        width: 100%;
        background-color: transparent;
    }

    .lib-panel .row,
    .lib-panel .col-md-6 {
        padding: 0;
        background-color: #ddd;
        min-height: 160px;
    }

    .lib-panel .lib-row {
        padding: 0 20px 0 20px;
    }

    .lib-panel .lib-row.lib-header {
        background-color: #ddd;
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
        border-bottom: 10px solid black;
    }

    .no-padding {
        padding: 0;
    }

    .tagy a {
        padding: 0 2px;
    }
    .tagy a img {
      width: 18px;
    }

    #addUser {
      color: #fff;
      background-color: #f16521;
      border-color: #ccc;
    }

</style>
