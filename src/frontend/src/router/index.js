import { createRouter, createWebHistory } from 'vue-router';
import RegisterProperty from '../components/RegisterProperty.vue';
import SignupPage from "@/components/LandlordLogin.vue"; // Ensure this path is correct
import LoginPage from "@/components/LoginPage.vue";
import StudentInbox from "@/components/StudentInbox.vue";
import AdminInbox from "@/components/LandLordInbox.vue";

const routes = [
    {
        path: '/register-property',
        name: 'RegisterProperty',
        component: RegisterProperty
    },
    {
        path:'/signupPage',
        name: 'SignupPage',
        component: SignupPage
    },
    {
        path:'/loginPage',
        name: 'LoginPage',
        component: LoginPage
    },
    {
        path: '/admin-inbox',
        name: 'AdminInbox',
        component: AdminInbox
    },
    {
        path: '/student-inbox',
        name: 'StudentInbox',
        component: StudentInbox
    },
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
});

export default router;
