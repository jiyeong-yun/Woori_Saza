<template>
    <div>
        <!-- {{articleNo}} -->
        <div>
            <b-row>
                <b-col cols="8">
                    <div>
                        <div :class="{statebox:true}">거래 완료</div>
                        <div style="font-size:1.1em;font-weight:bold;cursor:pointer" @click="moveToDetail">물 24개 나누실 분</div>
                        <div style="font-size:0.9em">기간: ~22.2.1</div>
                        <div style="font-size:0.9em">모집인원: 4/4</div>
                        
                    </div>
                </b-col>
                <b-col cols="4">
                    <div style="text-align:right" class="mt-5">
                        <div style="font-size:0.9em; text-decoration:line-through" class="mt-3">10000원</div>
                        <div style="font-size:1.3em;font-weight:bold;">2500원</div>
                    </div>
                </b-col>
            </b-row>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Article',
    props:["articleNo"],

    data() {
        return {
            
        };
    },

    mounted() {
        
    },

    methods: {
        moveToDetail(){
            this.$router.push("/board/"+this.articleNo).catch(()=>{});;
        },
    },
};
</script>

<style>
    
</style>