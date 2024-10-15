class StudentService {
    constructor() {
        this.apiUrl = '/api/StudentHomeBas/student';
    }


    async request(url, options) {
        try {
            const response = await fetch(url, options);
            if (!response.ok) {
                const errorDetails = await response.text();
                throw new Error(`Network response was not ok: ${response.statusText}. Details: ${errorDetails}`);
            }
            return options.method === 'DELETE' ? response.text() : response.json();
        } catch (error) {
            console.error('Fetch error:', error.message);
            throw error;
        }
    }


    async saveStudent(student) {
        const url = `${this.apiUrl}/save`;
        return await this.request(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(student),
        });
    }


    async readStudent(studentId) {
        const url = `${this.apiUrl}/read/${studentId}`;
        return await this.request(url, { method: 'GET' });
    }


    async updateStudent(student) {
        const url = `${this.apiUrl}/update`;
        return await this.request(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(student),
        });
    }


    async deleteStudent(studentId) {
        const url = `${this.apiUrl}/delete/${studentId}`;
        await this.request(url, { method: 'DELETE' });
        return studentId;
    }


    async fetchAllStudents() {
        const url = `${this.apiUrl}/getall`;
        return await this.request(url, { method: 'GET' });
    }
}

export default new StudentService();
