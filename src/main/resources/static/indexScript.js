document.addEventListener('DOMContentLoaded', async () => {
    const table = document.getElementById('equipmentTable').getElementsByTagName('tbody')[0];
    const modal = document.getElementById('modal');
    const editButton = document.getElementById('editButton');
    const deleteButton = document.getElementById('deleteButton');
    const searchInput = document.getElementById('searchInput');
    const filterSelect = document.getElementById('filterSelect');
    let selectedEquipmentId = null;
    let equipmentData = [];

    async function fetchData() {
        try {
            const response = await fetch('/api/v1/equipment');
            equipmentData = await response.json();
            renderTable(equipmentData);
        } catch (error) {
            console.error('Error fetching equipment:', error);
        }
    }

    function renderTable(data) {
        table.innerHTML = '';
        data.forEach(equipment => {
            const row = table.insertRow();
            row.setAttribute('data-id', equipment.id_equipment);

            row.insertCell(0).innerText = equipment.id_equipment;
            row.insertCell(1).innerText = equipment.category;
            row.insertCell(2).innerText = equipment.product_line;
            row.insertCell(3).innerText = equipment.name_product;
            row.insertCell(4).innerText = equipment.serial_number;
            row.insertCell(5).innerHTML = `
                <select class="status-select" data-id="${equipment.id_equipment}">
                    <option value="Active" ${equipment.status === 'Active' ? 'selected' : ''}>Active</option>
                    <option value="Inactive" ${equipment.status === 'Inactive' ? 'selected' : ''}>Inactive</option>
                    <option value="Maintenance" ${equipment.status === 'Maintenance' ? 'selected' : ''}>Maintenance</option>
                </select>
            `;
            row.insertCell(6).innerText = equipment.location;
            row.insertCell(7).innerHTML = '<button class="action-button">Выбрать</button>';

            row.addEventListener('click', (event) => {
                if (event.target.classList.contains('action-button')) {
                    selectedEquipmentId = equipment.id_equipment;
                    modal.style.display = 'block';
                }
            });

            // Обработчик события для изменения статуса
            const statusSelect = row.querySelector('.status-select');
            statusSelect.addEventListener('change', async (event) => {
                const newStatus = event.target.value;
                const equipmentId = event.target.getAttribute('data-id');

                const response = await fetch(`/api/v1/equipment/${equipmentId}/status`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        id_equipment_status: equipmentId,
                        location: equipment.location,
                        id_equipment: equipmentId,
                        date_start: new Date().toISOString(),
                        date_end: null,
                        current_status: newStatus,
                        id_responsible_company: 1,
                        id_responsible_customer_company: 2
                    })
                });

                if (response.ok) {
                    alert('Статус успешно обновлен!');
                } else {
                    alert('Ошибка при обновлении статуса');
                }
            });
        });
    }

    searchInput.addEventListener('input', (event) => {
        const filter = event.target.value.toLowerCase();
        const filteredData = equipmentData.filter(equipment => {
            return equipment.serial_number.toLowerCase().includes(filter) ||
                equipment.name_product.toLowerCase().includes(filter);
        });
        renderTable(filteredData);
    });

    filterSelect.addEventListener('change', (event) => {
        const filter = event.target.value;
        const filteredData = filter === 'all' ? equipmentData : equipmentData.filter(equipment => equipment.category.toLowerCase() === filter);
        renderTable(filteredData);
    });

    fetchData();

    editButton.addEventListener('click', () => {
        window.location.href = `/updateEquipment?id=${selectedEquipmentId}`;
    });

    deleteButton.addEventListener('click', async () => {
        try {
            const response = await fetch(`/api/v1/equipment/${selectedEquipmentId}`, {
                method: 'DELETE'
            });

            if (response.ok) {
                alert('Оборудование успешно удалено!');
                modal.style.display = 'none';
                location.reload();
            } else {
                const errorData = await response.json();
                alert('Ошибка при удалении оборудования: ' + errorData.message);
            }
        } catch (error) {
            alert('Ошибка при удалении оборудования: ' + error.message);
        }
    });

    document.getElementsByClassName('close')[0].onclick = function() {
        modal.style.display = 'none';
    };

    window.onclick = function(event) {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    };
});