<template>
    <div>
        <div class="mt-3" style="background-color : #F6F9FB ; width:100%;height:100%">
            <div class="pt-5 pb-5">
                <!-- pc버전 -->
                <b-container  v-if="windowWidth>=1000">
                    <b-row class="">
                        <b-col></b-col>
                        <b-col cols="7">
                            <div :class="{box:true}" style="overflow-y:auto;">
                                <div class="p-5">
                                    <PartyDetailCom> </PartyDetailCom>
                                </div>
                            </div>
                        </b-col>
                        <b-col>
                        </b-col>
                    </b-row>
                </b-container>
                <div :class="{box:true}" style="overflow-y:auto;" v-if="windowWidth<1000">
                    <div class="p-5">
                        <PartyDetailCom> </PartyDetailCom>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import PartyDetailCom from "@/components/PartyDetail/Review.vue";

export default {
    name: 'Review',
    components : {
        PartyDetailCom,
    },
    data() {
        return {
            windowWidth: window.innerWidth
        };
    },
    mounted() {
        window.onresize = () => {
            this.windowWidth = window.innerWidth
        }
    },

    methods: {
        
    },
};
</script>

<style scoped>
.box{
        background-color:white; 
        box-shadow: 0px 0px 5px 0.1px grey; 
        border-radius: 0.5em;
        width:100%;
        height:100%;
        -ms-overflow-style: none; /* IE and Edge */
        scrollbar-width: none; /* Firefox */
    }
</style>