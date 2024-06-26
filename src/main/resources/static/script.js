document.addEventListener('DOMContentLoaded', async () => {
    const table = document.getElementById('equipmentTable').getElementsByTagName('tbody')[0];
    const modal = document.getElementById('modal');
    const editButton = document.getElementById('editButton');
    const deleteButton = document.getElementById('deleteButton');
    const closeModal = document.querySelector('.close');
    let selectedEquipmentId = null;

    async function fetchData() {
        try {
            const response = await fetch('/api/v1/equipment');
            const data = await response.json();

            data.forEach(equipment => {
                const row = table.insertRow();
                row.setAttribute('data-id', equipment.id);

                row.insertCell(0).innerText = equipment.id;
                row.insertCell(1).innerText = equipment.category;
                row.insertCell(2).innerText = equipment.product_line;
                row.insertCell(3).innerText = equipment.name_product;
                row.insertCell(4).innerText = equipment.serial_number;
                row.insertCell(5).innerText = equipment.status;
                row.insertCell(6).innerText = equipment.location;
                row.insertCell(7).innerHTML = '<button class="action-button">Выбрать</button>';

                row.addEventListener('click', (event) => {
                    if (event.target.classList.contains('action-button')) {
                        selectedEquipmentId = equipment.id;
                        modal.style.display = 'block';
                    }
                });
            });
        } catch (error) {
            console.error('Error fetching equipment:', error);
        }
    }

    editButton.addEventListener('click', () => {
        if (selectedEquipmentId) {
            window.location.href = `/updateEquipment?id=${selectedEquipmentId}`;
        }
    });

    deleteButton.addEventListener('click', async () => {
        if (selectedEquipmentId) {
            try {
                await fetch(`/api/v1/equipment/${selectedEquipmentId}`, {
                    method: 'DELETE',
                });
                window.location.reload();
            } catch (error) {
                console.error('Error deleting equipment:', error);
            }
        }
    });

    closeModal.addEventListener('click', () => {
        modal.style.display = 'none';
    });

    window.addEventListener('click', (event) => {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    });

    fetchData();
});
