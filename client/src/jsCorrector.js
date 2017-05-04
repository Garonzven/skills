export default{

        data () {
            return {
                people: {
                    email: '',
                    pasword: ''
                },
                apiUrl: 'http://localhost:8080/WebSkillsGW7/webresources'
            }
        },
        mounted: function () {
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
