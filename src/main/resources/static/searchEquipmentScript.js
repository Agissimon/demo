document.getElementById('searchForm').addEventListener('submit', async function(event) {
    event.preventDefault();
    const searchValue = document.getElementById('search').value;
    const response = await fetch(`/api/v1/equipment/search?query=${searchValue}`);
    const data = await response.json();
    renderResults(data);
});

function renderResults(data) {
    const resultsDiv = document.getElementById('results');
    resultsDiv.innerHTML = '<table class="styled-table"><thead><tr><th>ID</th><th>Category</th><th>Product Line</th><th>Name Product</th><th>Serial Number</th><th>Status</th><th>Actions</th></tr></thead><tbody></tbody></table>';
    const tableBody = resultsDiv.querySelector('tbody');

    data.forEach(equipment => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${equipment.id_equipment}</td>
            <td>${equipment.category}</td>
            <td>${equipment.product_line}</td>
            <td>${equipment.name_product}</td>
            <td>${equipment.serial_number}</td>
            <td>
                <select data-id="${equipment.id_equipment}" class="status-select">
                    <option value="Active" ${equipment.status === 'Active' ? 'selected' : ''}>Active</option>
                    <option value="Inactive" ${equipment.status === 'Inactive' ? 'selected' : ''}>Inactive</option>
                    <option value="Maintenance" ${equipment.status === 'Maintenance' ? 'selected' : ''}>Maintenance</option>
                </select>
            </td>
            <td><button class="save-button" data-id="${equipment.id_equipment}">Save</button></td>
        `;
        tableBody.appendChild(row);
    });

    document.querySelectorAll('.save-button').forEach(button => {
        button.addEventListener('click', async function() {
            const id = this.getAttribute('data-id');
            const statusSelect = document.querySelector(`select[data-id="${id}"]`);
            const newStatus = statusSelect.value;

            const response = await fetch(`/api/v1/equipment/${id}/status`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    id_equipment_status: id,
                    location: "New Location",
                    id_equipment: id,
                    date_start: new Date().toISOString(),
                    date_end: null,
                    current_status: newStatus,
                    id_responsible_company: 1,
                    id_responsible_customer_company: 2
                })
            });

            if (response.ok) {
                alert('Status updated successfully!');
            } else {
                alert('Error updating status');
            }
        });
    });
}