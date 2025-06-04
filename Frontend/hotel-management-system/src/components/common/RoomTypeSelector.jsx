import React, { useEffect, useState } from "react";
import { getRoomTypes } from "../utils/ApiFunctions";

const RoomTypeSelector = ({ handleRoomInputChange, newRoom }) => {
  const [roomTypes, setRoomTypes] = useState([]);
  const [showNewRoomTypeInput, setShowNewRoomTypeInput] = useState(false);
  const [newRoomType, setNewRoomType] = useState("");

  useEffect(() => {
    getRoomTypes().then((data) => {
      setRoomTypes(data);
    });
    //console.log("roomTypes", data);
  }, []);
  const handleRoomTypeChange = (e) => {
    handleRoomInputChange(e);
  };

  const handleNewRoomTypeInputChange = (e) => {
    setNewRoomType(e.target.value);
  };

  const handleAddNewRoomType = () => {
    if (newRoomType !== "") {
      setRoomTypes([...roomTypes, newRoomType]);
      setNewRoomType("");
      setShowNewRoomTypeInput(false);
    }
  };

  return (
    <>
      {roomTypes.length > 0 && (
        <div>
          <select
            required
            name="roomType"
            className="form-select"
            value={newRoom.roomType}
            onChange={(e) => {
              console.log("room type:", newRoom.roomType);
              if (e.target.value === "Add New") {
                setShowNewRoomTypeInput(true);
              } else {
                console.log("e", e);
                handleRoomTypeChange(e);
              }
            }}
          >
            <option value="">Select a room type</option>
            <option value="Add New">Add New</option>
            {roomTypes.map((type, index) => (
              <option key={index} value={type}>
                {type}
              </option>
            ))}
          </select>
          {showNewRoomTypeInput && (
            <div className="mt-3">
              <div className="input-group">
                <input
                  className="form-control"
                  type="text"
                  placeholder="Enter new new room type"
                  onChange={handleNewRoomTypeInputChange}
                  value={newRoomType}
                />
                <button
                  className="btn-hotel"
                  type="button"
                  onClick={handleAddNewRoomType}
                >
                  Add
                </button>
              </div>
            </div>
          )}
        </div>
      )}
    </>
  );
};

export default RoomTypeSelector;
