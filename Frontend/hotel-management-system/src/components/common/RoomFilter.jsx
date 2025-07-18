import React, { useState } from "react";

const RoomFilter = ({ data, setFilteredData }) => {
  const [filter, setFilter] = useState("");

  const handleSelecetChange = (e) => {
    const selectedRoomType = e.target.value;
    setFilter(selectedRoomType);
    console.log("seleceted Room Type", selectedRoomType);
    const filteredRooms = data.filter((room) =>
      room.roomType.toLowerCase().includes(selectedRoomType.toLowerCase())
    );
    setFilteredData(filteredRooms);
    console.log("filtered rooms in room filter", filteredRooms);
  };

  const clearFilter = () => {
    setFilter("");
    setFilteredData(data);
  };

  const roomTypes = ["", ...new Set(data.map((room) => room.roomType))];

  return (
    <div className="input-group mb-3">
      <span className="input-group-text" id="room-type-filter">
        Filter rooms by type
      </span>
      <select
        className="form-select"
        aria-label="romm type filter"
        value={filter}
        onChange={handleSelecetChange}
      >
        <option value="">select a room type to filter....</option>

        {roomTypes.map((type, index) => (
          <option key={index} value={String(type)}>
            {String(type)}
          </option>
        ))}
      </select>

      <button className="btn-hotel" type="button" onClick={clearFilter}>
        Clear Filter
      </button>
    </div>
  );
};

export default RoomFilter;
