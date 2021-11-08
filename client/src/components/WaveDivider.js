import Wave from "./Wave";
export default function WaveDivider() {
    return (
        <div
            style={{
                display: "block",
                position: "absolute",
                bottom: "-250px",
                left: 0,
                width: "100vw",
            }}
        >
            <Wave angle="180" marginTop="0" marginBottom="0" />
            <Wave angle="0" marginTop="0" marginBottom="0" />
        </div>
    );
}
