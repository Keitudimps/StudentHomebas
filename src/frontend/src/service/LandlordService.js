class LandlordService {
    constructor() {
        this.apiUrl = '/api/StudentHomeBas/landlord'; // Base URL for the Landlord API
    }

    // Helper method to handle fetch requests
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

    // Delete a Landlord by ID
    async deleteLandlord(landlordId) {
        const url = `${this.apiUrl}/delete/${landlordId}`;
        await this.request(url, { method: 'DELETE' });
        return landlordId; // Return the landlord ID so the caller can update state
    }

    // Read a Landlord by ID
    async readLandlord(landlordId) {
        const url = `${this.apiUrl}/read/${landlordId}`;
        return await this.request(url, { method: 'GET' });
    }

    // Save a new Landlord
    async saveLandlord(landlord) {
        const url = `${this.apiUrl}/save`;
        return await this.request(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(landlord),
        });
    }

    // Update an existing Landlord
    async updateLandlord(landlord) {
        const url = `${this.apiUrl}/update`;
        return await this.request(url, {
            method: 'PUT', // Changed to PUT for update
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(landlord),
        });
    }

    // Fetch all Landlords
    async fetchAllLandlords() {
        const url = `${this.apiUrl}/getall`;
        return await this.request(url, { method: 'GET' });
    }
}

// Export an instance of the service class for reuse
export default new LandlordService();
